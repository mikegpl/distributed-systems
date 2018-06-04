package mikegpl.sr.services.remote

import mikegpl.sr.services.CurrencyService
import mikegpl.sr.thrift.Currency

class StreamingCurrencyService extends CurrencyService {
  override def getConversionRate(from: Currency, to: Currency): Double = {
    1.0
  }

  override def init(): CurrencyService = {
    // TODO: init grpc connection etc
    this
  }
}
