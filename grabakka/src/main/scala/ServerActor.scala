import akka.actor.Actor

final class ServerActor extends Actor {
  override def receive: Receive = {
    case m => println(m)
  }
}
