package mikegpl.sr.services

import mikegpl.sr.thrift._

import scala.collection.mutable.{HashMap => Map}
import scala.util.Try

object BankUtils {
  private val IncomeThreshold = 1919.0
  private val PeselLength = 11

  def getAccountType(person: Person): AccountType =
  //  todo - take advantage of currency conversion
    if (person.getIncome > IncomeThreshold) AccountType.PREMIUM else AccountType.STANDARD

  def isPeselValid(pesel: String): Boolean = {
    if (pesel.length == PeselLength)
      Try(pesel.toLong).isSuccess
    else
      false
  }

  def isEligibleForLoan(client: BankClient): Boolean = client.`type` == AccountType.PREMIUM
}
