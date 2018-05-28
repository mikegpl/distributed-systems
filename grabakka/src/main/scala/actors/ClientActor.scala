package actors

import akka.actor.{Actor, ActorSelection, Props}
import message._

final class ClientActor(serverPath: String) extends Actor {

  import ClientWorker._

  private val server = context.actorSelection(serverPath)

  override def receive: Receive = {
    case f: Requests.Find => context.actorOf(Props(FindWorker(f, server)))
    case o: Requests.Order => context.actorOf(Props(OrderWorker(o, server)))
    case s: Requests.Stream => context.actorOf(Props(StreamWorker(s, server)))
    case "q" =>
      println("Sielo")
      context.stop(self)
    case _ => println("received unknown message")
  }
}

sealed abstract class ClientWorker(order: ClientRequest, server: ActorSelection) extends Actor {
  {
    server ! order
  }

  override def receive: Receive = {
    case response: ServerResponse => handleResponse(response)
    case _ => unexpectedMessage()
  }

  def unexpectedMessage(): Unit = {
    println("Worker received invalid message")
    context.stop(self)
  }

  def handleResponse(value: ServerResponse)
}

object ClientWorker {

  case class FindWorker(order: Requests.Find, server: ActorSelection) extends ClientWorker(order, server) {
    override def handleResponse(response: ServerResponse): Unit = response match {
      case _: ServerResponse.NotFound =>
        println(s"Book ${order.title} not found")
        context.stop(self)
      case ServerResponse.Find.BookPrice(price) =>
        println(s"${order.title} costs $price")
        context.stop(self)
      case _ => unexpectedMessage()
    }
  }

  case class OrderWorker(order: Requests.Order, server: ActorSelection) extends ClientWorker(order, server) {
    override def handleResponse(response: ServerResponse): Unit = response match {
      case _: ServerResponse.NotFound =>
        println(s"Book ${order.title} not found")
      case _: ServerResponse.Order.Confirmation =>
        println(s"Order confirmed for ${order.title}")
        context.stop(self)
      case _ => unexpectedMessage()
    }
  }

  case class StreamWorker(stream: Requests.Stream, server: ActorSelection) extends ClientWorker(stream, server) {
    override def handleResponse(response: ServerResponse): Unit = response match {
      case _: ServerResponse.NotFound =>
        println(s"Book ${stream.title} not found")
        context.stop(self)
      case ServerResponse.Stream.NextLine(line) =>
        println(s"${stream.title} > $line")
      case _: ServerResponse.Stream.EndOfStream =>
        println(s"This is the end, beautiful friend... ${stream.title}")
        context.stop(self)
      case _ => unexpectedMessage()
    }
  }

}