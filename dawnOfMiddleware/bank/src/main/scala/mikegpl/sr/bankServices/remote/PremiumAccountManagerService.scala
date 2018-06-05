package mikegpl.sr.bankServices.remote

import mikegpl.sr.bankServices.{DbService, LoanService}
import mikegpl.sr.thrift.{LoanInquiry, LoanOffer, PremiumAccountManager}

class PremiumAccountManagerService(dbService: DbService, loanService: LoanService)
  extends StandardAccountManagerService(dbService) with PremiumAccountManager.Iface {
  override def getLoanConditions(inquiry: LoanInquiry): LoanOffer = loanService.loanConditions(inquiry)
}
