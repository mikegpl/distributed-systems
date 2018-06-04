package mikegpl.sr.services.internal

import mikegpl.sr.services.{BankUtils, LoanService}
import mikegpl.sr.thrift.{InvalidAccountTypeException, LoanOffer}

class PremiumLoanService(dbService: SynchronizedDbService) extends LoanService {
  def loanConditions(guid: Long): LoanOffer = {
    val client = dbService.getClient(guid)
    if (!BankUtils.isEligibleForLoan(client))
      throw new InvalidAccountTypeException(guid, s"Invalid account type: ${client.`type`}")

    new LoanOffer(200, 100)
  }
}
