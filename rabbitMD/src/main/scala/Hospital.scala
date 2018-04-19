import java.io.{BufferedReader, InputStreamReader}
import java.util.{Map => JMap}

import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client._

import scala.util.{Random, Try}

object Hospital {

  object TestType extends Enumeration {
    val Elbow, Knee, Hip = Value
  }

  import TestType._

  type TestPair = (TestType.Value, TestType.Value)
  final val TestPairs: Seq[TestPair] = Seq((Elbow, Knee), (Elbow, Hip), (Knee, Hip))

  private final val ConnectionFactory = {
    val factory = new ConnectionFactory
    factory.setHost("localhost")
    factory
  }
  final val ExchangeName = "grabkowyExchange"
  final val ExchangeType: BuiltinExchangeType = BuiltinExchangeType.TOPIC
  final val MessageEncoding = "UTF-8"
  final val MaxSleepTimeMilis = 5000

  def createAndSetupChannel(): Channel = {
    val channel = ConnectionFactory.newConnection().createChannel()
    channel.exchangeDeclare(ExchangeName, ExchangeType)
    channel
  }

  def isLegalTestType(name: String): Boolean =
    Try {
      TestType.withName(name)
    }.toOption.isDefined

  implicit class ChannelImprovements(val channel: Channel) {
    def queueDeclare(name: String): String = {
      channel.queueDeclare(name, false, false, false, null).getQueue
    }
  }

}


object Doctor extends App {

  import Hospital._

  final val channel = createAndSetupChannel()
  final val doctorId = Random.nextInt().abs

  final val responseQueueKey = s"doctor-$doctorId"
  final val responseQueue = channel.queueDeclare(responseQueueKey)
  final val responseQueueProperties = new BasicProperties().builder().replyTo(responseQueueKey).build()
  final val responseConsumer = new DefaultConsumer(channel) {
    override def handleDelivery(consumerTag: String, envelope: Envelope, properties: BasicProperties, body: Array[Byte]): Unit = {
      val message = new String(body, MessageEncoding)
      println(s"me< $message")
    }
  }

  channel.queueBind(responseQueue, ExchangeName, responseQueueKey)
  channel.basicConsume(responseQueueKey, true, responseConsumer)

  println(s"Dr. $doctorId, do you not realize that you're nothing but a large pair of scrubs to me?")
  var isRunning = true
  val consoleInput = new BufferedReader(new InputStreamReader(System.in))
  while (isRunning) {
    consoleInput.readLine().split(" ") match {
      case Array(patientName, testType) if isLegalTestType(testType) =>
        val message = s"$doctorId> request $testType for '$patientName'"
        channel.basicPublish(ExchangeName, testType, responseQueueProperties, message.getBytes(MessageEncoding))
      case Array("exit") =>
        isRunning = false
      case _ =>
        println("Sth went wrong")
    }
  }
  channel.close()
}

object Technician extends App {

  import Hospital._

  final val channel = createAndSetupChannel()
  final val technicianId = Random.nextInt().abs
  final val responseConsumer = new DefaultConsumer(channel) {
    override def handleDelivery(consumerTag: String, envelope: Envelope, properties: BasicProperties, body: Array[Byte]): Unit = {
      val message = new String(body, MessageEncoding)
      val reply = message.split("request") match {
        case Array(_, testInfo) => s"$technicianId performed $testInfo"
      }
      val senderKey = properties.getReplyTo
      println(s"Received request: '$message' FROM '$senderKey'")
      Thread.sleep(Random.nextInt(MaxSleepTimeMilis).abs)

      channel.basicPublish(ExchangeName, senderKey, null, reply.getBytes(MessageEncoding))
      channel.basicAck(envelope.getDeliveryTag, false)
    }
  }

  final val skills = TestPairs(Random.nextInt(TestPairs.size))
  println(s"technician no $technicianId with $skills")
  skills.productIterator.foreach { skill => {
    val skillQueue = channel.queueDeclare(skill.toString)
    channel.queueBind(skillQueue, ExchangeName, skillQueue)
    channel.basicConsume(skillQueue, false, responseConsumer)
  }
  }

  var isRunning = true
  while (isRunning) {
  }
}

