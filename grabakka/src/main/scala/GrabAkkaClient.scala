object GrabAkkaClient {
  def main(args: Array[String]): Unit = {
    println("Client")
  }
}

/*
cmd.readline() => mainActor.receive => find => FindActor().find
                                    => order => OrderActor().order
                                    => stream => StreamActor().stream
 */