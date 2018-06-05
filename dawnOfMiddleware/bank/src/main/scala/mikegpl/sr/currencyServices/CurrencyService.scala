package mikegpl.sr.currencyServices

import io.grpc.stub.StreamObserver
import mikegpl.sr.grpc.{ConvertersState, CurrencyConverter, CurrencyServiceGrpc, CurrencyStateRequest}

import scala.collection.JavaConverters._


class CurrencyService(dataProvider: CurrencyDataProvider) extends CurrencyServiceGrpc.CurrencyServiceImplBase {
  override def currentConverters(request: CurrencyStateRequest,
                                 responseObserver: StreamObserver[ConvertersState]): Unit = {
    println(s"Received request for current converters for ${request.getCurrencyList.asScala.mkString(" ")}")
    val observedCurrencies = request.getCurrencyList.asScala.toSet
    val resultState = ConvertersState.newBuilder()
      .addAllConverter(dataProvider.currentConverters(observedCurrencies))
      .build()
    println(s"Result state for request :\n ${resultState.getConverterList.asScala.map(c => c.getName + " " + c.getRate).mkString("\n")}")
    responseObserver.onNext(resultState)
    responseObserver.onCompleted()
  }

  override def streamConverters(request: CurrencyStateRequest,
                                responseObserver: StreamObserver[CurrencyConverter]): Unit = {
    println(s"Received request for streaming converters for ${request.getCurrencyList.asScala.mkString(" ")}")
    val observedCurrencies = request.getCurrencyList.asScala.toSet
    dataProvider.subscribe(observedCurrencies)
      .subscribe(newConverter => responseObserver.onNext(newConverter))
  }
}
