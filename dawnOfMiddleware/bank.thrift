namespace java mikegpl.sr.thrift
namespace py mikegpl.sr.thrift


enum Currency {
	PLN = 0,
	USD = 1,
	EUR = 2,
	VEF = 3
}

enum AccountType {
	STANDARD = 0,
	PREMIUM = 1
}

struct Person {
	1: string name,
	2: string surname,
	3: string pesel,
	4: double income,
	5: Currency baseCurrency
}

struct BankClient {
	1: Person owner,
	2: AccountType type,
	3: i64 guid
}

struct LoanInquiry {
	1: i64 guid,
	2: double amount,
	3: Currency currency
}

struct LoanOffer {
	1: double baseCurrencyCost,
	2: double requestedCurrencyCost
}

exception InvalidPeselException {
	1: string pesel,
	2: string reason
}

exception ClientExistsException {
	1: i64 guid,
	2: string reason
}

service RegistrationManager {
	BankClient registerClient(1: Person Person) throws (1: InvalidPeselException e1, 2: ClientExistsException e2)
}

service StandardAccountManager {
	BankClient getClientForGuid(1:i64 guid) throws (1: ClientExistsException e)
}

service PremiumAccountManager extends StandardAccountManager {
	LoanOffer getLoanConditionsForGuid(1:i64 guid) throws (1: ClientExistsException e)
}