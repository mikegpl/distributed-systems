package message

sealed trait ServerResponse

object Responses {

  case class NotFound(title: String) extends ServerResponse

  object Find {

    case class BookPrice(price: Double) extends ServerResponse

  }

  object Order {

    case class Confirmation(title: String) extends ServerResponse

  }

  object Stream {

    case class NextLine(line: String) extends ServerResponse

    case class EndOfStream(title: String) extends ServerResponse

  }

}