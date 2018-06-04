package mikegpl.sr.remoteServices

import mikegpl.sr.AccountService
import mikegpl.sr.thrift.{LoanOffer, PremiumAccountManager}

class PremiumAccountManagerService extends StandardAccountManagerService with PremiumAccountManager.Iface {
  override def getLoanConditionsForGuid(guid: Long): LoanOffer = {
    AccountService.loanConditions(guid)
  }
}
