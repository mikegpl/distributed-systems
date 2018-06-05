package mikegpl.sr.currencyServices

import java.lang.{Iterable => jIterable}

import mikegpl.sr.grpc.{Currency, CurrencyConverter}
import rx.lang.scala.Observable
import rx.lang.scala.subjects.ReplaySubject

import scala.collection.JavaConverters._
import scala.collection.mutable.{HashMap => Map}
import scala.collection.{Set, mutable}

class SimpleCurrencyDataProvider extends CurrencyDataProvider {
  private val converters = mutable.HashSet[CurrencyConverter]()
  private val subscriberMap = Map[ReplaySubject[CurrencyConverter], Set[Currency]]()

  override def currentConverters(currencies: Set[Currency]): jIterable[CurrencyConverter] = {
    converters.synchronized {
      converters.filter(converter => currencies.contains(converter.getName)).asJava
    }
  }

  override def subscribe(currencies: Set[Currency]): Observable[CurrencyConverter] = {
    val observable = ReplaySubject[CurrencyConverter]()
    subscriberMap.synchronized {
      subscriberMap += (observable -> currencies)
    }
    observable
  }

  override def newConverter(currencyConverter: CurrencyConverter): Unit = {
    converters.synchronized {
      converters += currencyConverter
    }
    subscriberMap.synchronized {
      subscriberMap.foreach { case (subject, currencies) =>
        if (currencies.contains(currencyConverter.getName)) {
          subject.onNext(currencyConverter)
        }
      }
    }
  }
}
