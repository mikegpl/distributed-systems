package mikegpl.sr.services.remote

import mikegpl.sr.services.{DbService, LoanService}
import mikegpl.sr.thrift.{LoanInquiry, LoanOffer, PremiumAccountManager}

class PremiumAccountManagerService(dbService: DbService, loanService: LoanService)
  extends StandardAccountManagerService(dbService) with PremiumAccountManager.Iface {
  override def getLoanConditions(inquiry: LoanInquiry): LoanOffer = {
    loanService.loanConditions(inquiry.guid)
  }
}
