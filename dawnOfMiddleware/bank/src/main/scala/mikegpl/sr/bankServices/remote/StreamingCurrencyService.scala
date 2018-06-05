package mikegpl.sr.bankServices.remote

import java.util.concurrent.Executors

import io.grpc.ManagedChannelBuilder
import mikegpl.sr.CurrencyServer
import mikegpl.sr.bankServices.CurrencyService
import mikegpl.sr.grpc.{CurrencyServiceGrpc, CurrencyStateRequest, Currency => gCurrency}
import mikegpl.sr.thrift.{Currency => tCurrency}

import scala.collection.JavaConverters._
import scala.collection.concurrent.{TrieMap => Map}


class StreamingCurrencyService extends CurrencyService {

  private val currencyConverters: Map[gCurrency, Double] = Map[gCurrency, Double]()
  private val serviceStub = {
    val channel = ManagedChannelBuilder.forAddress(CurrencyServer.Host, CurrencyServer.Port)
      .usePlaintext()
      .build()

    CurrencyServiceGrpc.newBlockingStub(channel)
  }
  private val updateExecutor = Executors.newSingleThreadExecutor()

  override def getConversionRate(from: tCurrency, to: tCurrency = tCurrency.PLN): Double = currencyConverters.getOrElse(from, 1.0) / currencyConverters.getOrElse(to, 1.0)

  override def init(): CurrencyService = {
    println("Initiating StreamingCurrencyService")
    val initRequest = CurrencyStateRequest.newBuilder()
      .addAllCurrency(gCurrency.values().toIterable.filterNot(_ == gCurrency.UNRECOGNIZED).asJava)
      .build()

    serviceStub.currentConverters(initRequest).getConverterList.asScala
      .foreach(converter => currencyConverters.put(converter.getName, converter.getRate))

    println(s"Received initial conversion rates: ${currencyConverters.map { case (c, r) => c + "->" + r }.mkString(" ")}")
    updateExecutor.submit(new Runnable {
      override def run(): Unit = {
        serviceStub.streamConverters(initRequest)
          .forEachRemaining(updatedConverter => {
            println(s"Registered updated value for ${updatedConverter.getName} -> ${updatedConverter.getRate}")
            currencyConverters.put(updatedConverter.getName, updatedConverter.getRate)
          })
      }
    })
    this
  }
}

