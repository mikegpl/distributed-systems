import java.io._
import java.net.{ServerSocket, Socket}
import java.util.concurrent.Executors

import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable

object ChatServerLauncher extends LazyLogging {
  val Port = 9989

  def main(args: Array[String]): Unit = {
    logger.info("Server launcher on")
    val server = new SocketChatServer()
    try {
      server.run()
    } catch {
      case e: IOException =>
        logger.error("IOError", e)
    }
  }
}


class SocketChatServer(port: Int = ChatServerLauncher.Port, threadPoolSize: Int = 10) extends LazyLogging {
  logger.info(s"Server instantiated on $port")
  private val clientThreadPool = Executors.newWorkStealingPool(threadPoolSize)
  private val nicksToClientHandlers = new mutable.HashMap[String, ClientConnectionHandler]()
  private var serverSocket: ServerSocket = _

  def run(): Unit = {
    try {
      serverSocket = new ServerSocket(port)
      while (true) {
        val clientSocket = serverSocket.accept()
        logger.info(s"Incoming client at ${clientSocket.getInetAddress}")
        val clientHandler = new ClientConnectionHandler(clientSocket, this)
        this.synchronized {
          nicksToClientHandlers.put(clientHandler.getNick, clientHandler)
        }
        clientThreadPool.submit(clientHandler)
      }
    } catch {
      case e: IOException => logger.error("Server socket error", e)
    } finally {
      serverSocket.close()
    }
  }

  def broadcastTcp(message: String, nick: String): Unit = {
    logger.info(s"trying to broadcast '$message' from $nick")
    val formattedMessage = s"server#$nick#$message"
    this.synchronized {
      nicksToClientHandlers
        .foreach { case (clientsNick, handler) =>
          if (clientsNick != nick)
            handler.unicastTcp(formattedMessage)
        }
    }
  }

  def logoutClient(nick: String): Unit = this.synchronized {
    nicksToClientHandlers.remove(nick)
  }
}

class ClientConnectionHandler(socket: Socket, server: SocketChatServer) extends Runnable with LazyLogging {
  private val outbox = new DataOutputStream(socket.getOutputStream)
  private val inbox = new DataInputStream(socket.getInputStream)
  private val nick = {
    inbox.readUTF().split("#") match {
      case Array("login", clientNick) =>
        logger.info(s"Client logged $clientNick")
        Some(clientNick)
      case msg: Array[String] =>
        logger.error("Login message pattern mismatch " + msg.mkString(" "))
        None
    }
  }
  private var loggedIn = nick.isDefined

  def getNick: String = nick.getOrElse("unknown")

  override def run(): Unit = {
    try {
      outbox.writeUTF("Logged in")
      while (loggedIn) {
        val messageReceived = inbox.readUTF()
        messageReceived.split("#") match {
          case Array("client", msg) => server.broadcastTcp(msg, getNick)
          case Array("logout", clientNick) if clientNick == getNick =>
            loggedIn = false
          case _: Array[String] =>
            loggedIn = false
            logger.error(s"Client message error $getNick", messageReceived)
        }
      }
    } catch {
      case _: IOException | _: EOFException =>
        logger.error(s"Client $getNick logged out")
        loggedIn = false
    } finally {
      logger.error(s"Logging out client $getNick")
      server.logoutClient(getNick)
      socket.close()
    }
  }

  def unicastTcp(formattedMessage: String): Unit = {
    logger.info(s"trying to unicast $formattedMessage to $getNick")
    outbox.writeUTF(formattedMessage)
  }
}

