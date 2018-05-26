import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import message.Requests._

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
        case Array("f", title) => actor ! Find(title.trim)
        case Array("o", title) => actor ! Order(title.trim)
        case Array("s", title) => actor ! Stream(title.trim)
        case _ =>
          actor ! "q"
          isRunning = false
      }
    }
  }
}
