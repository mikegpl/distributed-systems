package mikegpl.sr.remoteServices

import mikegpl.sr.AccountService
import mikegpl.sr.thrift.{LoanInquiry, LoanOffer, PremiumAccountManager}

class PremiumAccountManagerService extends StandardAccountManagerService with PremiumAccountManager.Iface {
  override def getLoanConditions(inquiry: LoanInquiry): LoanOffer = {
    AccountService.loanConditions(inquiry.guid)
  }
}
