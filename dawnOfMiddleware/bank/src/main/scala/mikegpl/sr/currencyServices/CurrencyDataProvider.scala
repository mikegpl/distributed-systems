package mikegpl.sr.currencyServices

import java.lang.{Iterable => jIterable}

import mikegpl.sr.grpc.{Currency, CurrencyConverter}
import rx.lang.scala.Observable

import scala.collection.Set

trait CurrencyDataProvider {
  def currentConverters(currencies: Set[Currency]): jIterable[CurrencyConverter]

  def subscribe(currencies: Set[Currency]): Observable[CurrencyConverter]

  def newConverter(currencyConverter: CurrencyConverter)
}
