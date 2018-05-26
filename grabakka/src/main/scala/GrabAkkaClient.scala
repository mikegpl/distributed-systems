import java.io.File

import message.Requests._
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

object GrabAkkaClient {
  val ConfigPath = "./client.conf"

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.parseFile(new File(ConfigPath))
    val system = ActorSystem(config.getString("system-name"), config)
    val actor = system.actorOf(Props(new ClientActor(config.getString("remote-path"))))

    println("f/o/s#title")
    var isRunning = true
    while (isRunning) {
      val input = StdIn.readLine()
      input.split("#") match {
        case Array("f", title) => actor ! Find(title)
        case Array("o", title) => actor ! Order(title)
        case Array("s", title) => actor ! Stream(title)
        case Array("q") =>
          actor ! "q"
          isRunning = false
      }
    }
  }
}
