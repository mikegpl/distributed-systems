package mikegpl.sr.bankServices

import mikegpl.sr.grpc.{Currency => gCurrency}
import mikegpl.sr.thrift.{BankClient, LoanInquiry, LoanOffer, Currency => tCurrency}

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
  private val thriftToGrpcCurrency: Map[tCurrency, gCurrency] =
    tCurrency.values().zip(gCurrency.values()).map { case (tc, gc) => tc -> gc }.toMap

  protected implicit def thriftToGrpc(currency: tCurrency): gCurrency = thriftToGrpcCurrency(currency)

  def getCurrencies: Set[tCurrency] = thriftToGrpcCurrency.keySet

  def getConversionRate(from: tCurrency, to: tCurrency = tCurrency.PLN): Double

  def init(): CurrencyService
}

