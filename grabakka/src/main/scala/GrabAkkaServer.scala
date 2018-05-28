import java.io.File

import actors.ServerActor
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object GrabAkkaServer {
  val ConfigPath = "./server.conf"

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.parseFile(new File(ConfigPath))
    val system = ActorSystem(config.getString("system-name"), config)
    val actor = system.actorOf(Props(new ServerActor()), config.getString("actor-name"))
    while (true) {}
  }
}