package mikegpl.sr

import mikegpl.sr.thrift._

import scala.collection.mutable.{HashMap => Map}
import scala.util.Try

// todo - SRP-ize and OCP-ize
object AccountService {
  private val IncomeThreshold = 1919.0
  private val PeselLength = 11
  private val clientMap = Map[Long, BankClient]()

  def getAccountType(person: Person): AccountType =
  //  todo - take advantage of currency conversion
    if (person.getIncome > IncomeThreshold) AccountType.PREMIUM else AccountType.STANDARD

  def registerClient(client: BankClient): Unit = {
    clientMap.synchronized {
      if (clientMap.contains(client.guid))
        throw new ClientExistsException(client.guid)

      clientMap += (client.guid -> client)
    }
  }

  def getClient(guid: Long): BankClient = {
    clientMap.synchronized {
      if (!clientMap.contains(guid))
        throw new ClientDoesNotExistException(guid)

      clientMap(guid)
    }
  }

  def isPeselValid(pesel: String): Boolean = {
    if (pesel.length == PeselLength)
      Try(pesel.toLong).isSuccess
    else
      false
  }

  def loanConditions(guid: Long): LoanOffer = {
    val client = getClient(guid)
    if (client.`type` != AccountType.PREMIUM)
      throw new InvalidAccountTypeException(guid, s"Invalid account type: ${client.`type` }")

    new LoanOffer(200, 100)
  }
}
