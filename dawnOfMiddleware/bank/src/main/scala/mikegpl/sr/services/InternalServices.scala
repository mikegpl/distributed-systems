package mikegpl.sr.services

import mikegpl.sr.thrift.{BankClient, LoanOffer}

trait DbService {
  def registerClient(client: BankClient): Unit

  def getClient(guid: Long): BankClient
}

trait LoanService {
  def loanConditions(guid: Long): LoanOffer
}

