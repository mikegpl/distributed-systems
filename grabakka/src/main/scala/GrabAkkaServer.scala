object GrabAkkaServer {
  def main(args: Array[String]): Unit = {
    println("Server")
  }
}

/*
mainActor.receive => :Find   => FindActor().find
                  => :Stream => StreamActor().stream
                  => :Order  => OrderActor().order

 */