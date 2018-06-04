package mikegpl.sr.services

import com.google.common.collect.{BiMap, HashBiMap}
import mikegpl.sr.grpc.{Currency => gCurrency}
import mikegpl.sr.thrift.{BankClient, LoanInquiry, LoanOffer, Currency => tCurrency}

import scala.collection.JavaConverters._
import scala.collection.Set

trait DbService {
  def registerClient(client: BankClient): Unit

  def getClient(guid: Long): BankClient
}

trait LoanService {
  def loanConditions(inquiry: LoanInquiry): LoanOffer

  protected def loanCost(amount: Double, conversionRate: Double): Double
}

trait CurrencyService {
  private val currencyBiMap: BiMap[gCurrency, tCurrency] = {
    val map = new HashBiMap[gCurrency, tCurrency]()
    gCurrency.values().zip(tCurrency.values()).foreach { case (gc, tc) => map.put(gc, tc) }
    map
  }

  protected def grpcToThrift(currency: gCurrency): tCurrency = currencyBiMap.get(currency)

  protected def thriftToGrpc(currency: tCurrency): gCurrency = currencyBiMap.inverse().get(currency)

  def getCurrencies: Set[tCurrency] = currencyBiMap.values().asScala

  def getConversionRate(from: tCurrency, to: tCurrency): Double

  def init(): CurrencyService
}

