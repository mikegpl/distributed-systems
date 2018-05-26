package message

sealed trait ClientRequest

object Requests {

  case class Find(title: String) extends ClientRequest

  case class Order(title: String) extends ClientRequest

  case class Stream(title: String) extends ClientRequest

}
