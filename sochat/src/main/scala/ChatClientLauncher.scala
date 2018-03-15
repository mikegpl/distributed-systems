import java.io.{DataInputStream, DataOutputStream, EOFException, IOException}
import java.net.{InetAddress, Socket}

import com.typesafe.scalalogging.LazyLogging

object ChatClientLauncher extends LazyLogging {
  val HostName = "localhost"

  def main(args: Array[String]): Unit = {
    logger.info("Client launcher on")
    val scanner = scala.io.StdIn
    val name = scanner.readLine()
    val client = ChatClient(name)
    val connectionThread = new Thread(client)
    connectionThread.start()
    val readThread = new Thread(new SocketChatScanner(client))
    readThread.start()
  }
}

class SocketChatScanner(client: ChatClient) extends Runnable {
  override def run(): Unit = {
    while (true) {
      val msg = scala.io.StdIn.readLine()
      client.sendMessage(msg)
    }
  }
}

case class ChatClient(nick: String = "Zbyszek") extends Runnable with LazyLogging {
  logger.info(s"'$nick' created on ${ChatServerLauncher.Port}")
  private val socket = new Socket(InetAddress.getByName(ChatClientLauncher.HostName), ChatServerLauncher.Port)
  private val outbox = new DataOutputStream(socket.getOutputStream)
  private val inbox = new DataInputStream(socket.getInputStream)
  private var isRunning = true

  def run(): Unit = {
    login()
    while (isRunning) {
      try {
        val msg = inbox.readUTF()
        msg.split("#") match {
          case Array("server", source, message) =>
            println(s"$source said: '$message'")
          case _ =>
            logger.warn(s"Received unknown message '$msg'")
        }
      } catch {
        case e: EOFException =>
          logger.warn("Server quit unexpectedly")
          System.exit(1)
      }
    }
    logger.info("Logging out")
    logout()
  }

  def login(): Unit = {
    try {
      outbox.writeUTF(s"login#$nick")
    } catch {
      case e: IOException =>
        isRunning = false
    }
  }

  def logout(): Unit = {
    try {
      outbox.writeUTF(s"logout#$nick")
    } catch {
      case e: IOException =>
        isRunning = false
    } finally {
      socket.close()
    }
  }

  def sendMessage(msg: String): Unit = {
    try {
      outbox.writeUTF(s"client#$msg")
    } catch {
      case e: IOException =>
        isRunning = false
    }
  }
}