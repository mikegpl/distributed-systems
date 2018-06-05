package mikegpl.sr.bankServices

import mikegpl.sr.thrift._

import scala.collection.mutable.{HashMap => Map}
import scala.util.Try

object BankUtils {
  private val IncomeThreshold = 1919.0
  private val PeselLength = 11

  def getAccountType(person: Person, currencyService: CurrencyService): AccountType = {
    val income = person.getIncome * currencyService.getConversionRate(person.baseCurrency)
    if (income > IncomeThreshold) AccountType.PREMIUM else AccountType.STANDARD
  }

  def isPeselValid(pesel: String): Boolean = {
    if (pesel.length == PeselLength)
      Try(pesel.toLong).isSuccess
    else
      false
  }

  def isEligibleForLoan(client: BankClient): Boolean = client.`type` == AccountType.PREMIUM
}
