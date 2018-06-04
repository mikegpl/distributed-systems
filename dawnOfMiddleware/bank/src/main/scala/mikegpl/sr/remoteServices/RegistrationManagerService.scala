package mikegpl.sr.remoteServices

import mikegpl.sr.AccountService
import mikegpl.sr.thrift.{BankClient, InvalidPeselException, Person, RegistrationManager}

class RegistrationManagerService extends RegistrationManager.Iface {
  override def registerClient(person: Person): BankClient = {
    if (!AccountService.isPeselValid(person.pesel))
      throw new InvalidPeselException(person.pesel, "Hey yaaaa")
    val client = new BankClient(person, AccountService.getAccountType(person), person.pesel.hashCode.abs)
    AccountService.registerClient(client)
    client
  }
}
