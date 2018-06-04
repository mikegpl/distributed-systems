package mikegpl.sr.remoteServices

import mikegpl.sr.AccountService
import mikegpl.sr.thrift.{BankClient, StandardAccountManager}

class StandardAccountManagerService extends StandardAccountManager.Iface {
  override def getClientForGuid(guid: Long): BankClient = {
    AccountService.getClient(guid)
  }
}
