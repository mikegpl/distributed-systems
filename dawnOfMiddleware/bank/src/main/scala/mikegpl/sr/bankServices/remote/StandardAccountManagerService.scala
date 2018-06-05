package mikegpl.sr.bankServices.remote

import mikegpl.sr.bankServices.DbService
import mikegpl.sr.thrift.{BankClient, StandardAccountManager}

class StandardAccountManagerService(dbService: DbService) extends StandardAccountManager.Iface {
  override def getClientForGuid(guid: Long): BankClient = {
    dbService.getClient(guid)
  }
}
