package mikegpl.sr.currencyServices

import io.grpc.stub.StreamObserver
import mikegpl.sr.grpc.{ConvertersState, CurrencyConverter, CurrencyServiceGrpc, CurrencyStateRequest}

import scala.collection.JavaConverters._


class CurrencyService(dataProvider: CurrencyDataProvider) extends CurrencyServiceGrpc.CurrencyServiceImplBase {
  override def currentConverters(request: CurrencyStateRequest,
                                 responseObserver: StreamObserver[ConvertersState]): Unit = {
    val observedCurrencies = request.getCurrencyList.asScala.toSet
    val resultState = ConvertersState.newBuilder()
      .addAllConverter(dataProvider.currentConverters(observedCurrencies))
      .build()

    responseObserver.onNext(resultState)
  }

  override def streamConverters(request: CurrencyStateRequest,
                                responseObserver: StreamObserver[CurrencyConverter]): Unit = {
    val observedCurrencies = request.getCurrencyList.asScala.toSet
    dataProvider.subscribe(observedCurrencies)
      .subscribe(newConverter => responseObserver.onNext(newConverter))
  }
}
