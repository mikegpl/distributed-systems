package mikegpl.sr.services.remote

import mikegpl.sr.services.{BankUtils, DbService}
import mikegpl.sr.thrift.{BankClient, InvalidPeselException, Person, RegistrationManager}

class RegistrationManagerService(dbService: DbService) extends RegistrationManager.Iface {
  override def registerClient(person: Person): BankClient = {
    if (!BankUtils.isPeselValid(person.pesel))
      throw new InvalidPeselException(person.pesel, "Somebody once told me...")

    val client = new BankClient(person, BankUtils.getAccountType(person), person.pesel.hashCode.abs)
    dbService.registerClient(client)
    client
  }
}
