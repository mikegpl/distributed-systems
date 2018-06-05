package mikegpl.sr.services.remote

import mikegpl.sr.services.{BankUtils, CurrencyService, DbService}
import mikegpl.sr.thrift.{BankClient, InvalidPeselException, Person, RegistrationManager}

class RegistrationManagerService(dbService: DbService, currencyService: CurrencyService) extends RegistrationManager.Iface {
  override def registerClient(person: Person): BankClient = {
    if (!BankUtils.isPeselValid(person.pesel))
      throw new InvalidPeselException(person.pesel, "Somebody once told me...")

    val client = new BankClient(person, BankUtils.getAccountType(person, currencyService), person.pesel.hashCode.abs)
    dbService.registerClient(client)
    client
  }
}
