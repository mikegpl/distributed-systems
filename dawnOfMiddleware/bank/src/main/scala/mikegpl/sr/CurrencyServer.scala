package mikegpl.sr

import java.util.concurrent.Executors

import io.grpc.ServerBuilder
import mikegpl.sr.currencyServices.{CurrencyService, SimpleCurrencyDataProvider}
import mikegpl.sr.grpc.Currency._
import mikegpl.sr.grpc.{Currency, CurrencyConverter}

import scala.collection.mutable.{HashMap => Map}
import scala.util.Random

object CurrencyServer {
  val Host = "localhost"
  val Port = 19191
  private val Rates = Map(PLN -> 1.0, USD -> 3.5, EUR -> 4.0, VEF -> 1e-5)


  def main(args: Array[String]): Unit = {
    val executor = Executors.newSingleThreadExecutor()
    val dataProvider = new SimpleCurrencyDataProvider
    val currencyService = new CurrencyService(dataProvider)
    val server = ServerBuilder
      .forPort(Port)
      .addService(currencyService)
      .build().start()
    println(s"CurrencyServer started on $Port")
    Rates.map { case (currency, rate) => toConverter(currency, rate) }.foreach(dataProvider.newConverter)

    executor.submit(new Runnable {
      override def run(): Unit = {
        println("Currency value changing service started")
        while (true) {
          Thread.sleep(10000)
          val currency = Currency.forNumber(Random.nextInt(Rates.size))
          val changeSign = Random.nextInt().signum
          val change = Random.nextDouble() / 100
          val newValue = Rates(currency) * (1 + change * changeSign)
          Rates.put(currency, newValue)
          dataProvider.newConverter(toConverter(currency, newValue))
        }
      }
    })
    server.awaitTermination()
  }

  def toConverter(currency: Currency, rate: Double): CurrencyConverter = CurrencyConverter.newBuilder()
    .setName(currency)
    .setRate(rate)
    .build()
}
