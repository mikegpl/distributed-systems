package actors

import java.io.{File, FileNotFoundException}
import java.nio.file.{Files, Paths, StandardOpenOption}

import actors.ServerWorker.{FindWorker, OrderWorker, StreamWorker}
import akka.Done
import akka.actor.SupervisorStrategy.{Resume, Stop}
import akka.actor.{Actor, ActorRef, OneForOneStrategy, Props}
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.{ActorMaterializer, ActorMaterializerSettings, ThrottleMode}
import message.{ClientRequest, Requests, ServerResponse}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._


final class ServerActor extends Actor {
  override val supervisorStrategy: OneForOneStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
      case _: Throwable => Resume
    }

  override def receive: Receive = {
    case f: Requests.Find =>
      val worker = context.actorOf(Props(FindWorker()))
      worker.forward(f)(context)
    case o: Requests.Order =>
      val worker = context.child("order-worker").get
      worker.forward(message = o)(context)
    case s: Requests.Stream =>
      val worker = context.actorOf(Props(StreamWorker()))
      worker.forward(message = s)(context)
    case _ => println("received unknown message")
  }

  override def preStart(): Unit = {
    context.actorOf(Props(OrderWorker()), "order-worker")
  }
}


sealed abstract class ServerWorker() extends Actor {

  override def receive: Receive = {
    case request: ClientRequest => handleRequest(request)
    case response: ServerResponse => handleResponse(response)
    case _ => unexpectedMessage()
  }

  def handleResponse(response: ServerResponse): Unit = unexpectedMessage()

  def unexpectedMessage(): Unit = {
    println("Worker received invalid message")
    context.stop(self)
  }

  def handleRequest(request: ClientRequest): Unit

}

object ServerWorker {
  private val DbFileNames = Seq("db1.txt", "db2.txt")
  private val OrderFileName = "orders.txt"

  case class FindWorker() extends ServerWorker {
    private var responsesCount = 0
    private var requestSender: ActorRef = _

    override def handleRequest(request: ClientRequest): Unit = request match {
      case f: Requests.Find =>
        requestSender = sender()
        DbFileNames.map(name => context.actorOf(Props(DatabaseWorker(name)), name))
          .foreach { name =>
            name ! f
          }
      case _ => unexpectedMessage()
    }

    override def handleResponse(response: ServerResponse): Unit = response match {
      case notFound: ServerResponse.NotFound =>
        responsesCount += 1
        if (responsesCount == 2) {
          requestSender ! notFound
          context.stop(self)
        }
      case price: ServerResponse.Find.BookPrice =>
        requestSender ! price
        context.stop(self)
      case _ => unexpectedMessage()
    }


    private case class DatabaseWorker(filename: String) extends Actor {
      override val supervisorStrategy: OneForOneStrategy =
        OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
          case _: FileNotFoundException =>
            sender ! ServerResponse.NotFound()
            Stop
          case _: NumberFormatException => Resume
          case _: Throwable => Stop
        }

      override def receive: Receive = {
        case Requests.Find(title) =>
          findInDb(title)
        case _ => context.stop(self)
      }

      private def findInDb(title: String): Unit = {
        val response = scala.io.Source.fromFile(new File(filename)).getLines().toStream
          .map { line =>
            val Array(titleString, priceString) = line.split("#")
            (titleString.trim, priceString.toDouble)
          }
          .find { case (bookTitle: String, _: Double) => title == bookTitle }
          .map { case (_: String, price: Double) => ServerResponse.Find.BookPrice(price) }
          .getOrElse(ServerResponse.NotFound())

        sender ! response
      }
    }

  }

  case class OrderWorker() extends ServerWorker {
    override def handleRequest(requests: ClientRequest): Unit = requests match {
      case Requests.Order(title) => makeOrder(title)
      case _ => unexpectedMessage()
    }

    def makeOrder(title: String): Unit = {
      try {
        synchronized {
          Files.write(Paths.get(OrderFileName), (title + "\n").getBytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        }
        sender ! ServerResponse.Order.Confirmation(title)
      }
      catch {
        case _: Throwable => sender ! ServerResponse.NotFound()
      }
    }
  }

  case class StreamWorker() extends ServerWorker {
    private var target: ActorRef = _

    override def handleRequest(request: ClientRequest): Unit = request match {
      case Requests.Stream(title) =>
        streamBook(title)
        target = sender
      case _ => unexpectedMessage()
    }

    implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))

    private def streamBook(title: String): Unit = {
      val fileName = s"./books/$title.txt"
      val sink: Sink[String, Future[Done]] = Sink.foreach(line => target ! ServerResponse.Stream.NextLine(line))
      Source.fromIterator(() => scala.io.Source.fromFile(fileName).getLines())
        .throttle(elements = 1, per = 1.second, maximumBurst = 1, mode = ThrottleMode.shaping)
        .runWith(sink)
        .recover { case _: Throwable => target ! ServerResponse.NotFound() }
        .onComplete(_ => target ! ServerResponse.Stream.EndOfStream)
    }
  }

}