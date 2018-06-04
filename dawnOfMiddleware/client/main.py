import sys

from thrift.protocol import TBinaryProtocol, TMultiplexedProtocol
from thrift.transport import TSocket, TTransport

sys.path.append('gen_py')

# services
from gen_py.mikegpl.sr.thrift import RegistrationManager, StandardAccountManager, PremiumAccountManager
# types
from gen_py.mikegpl.sr.thrift.ttypes import Currency, Person, ClientDoesNotExistException, InvalidAccountTypeException, \
    LoanInquiry, AccountType
# exceptions
from gen_py.mikegpl.sr.thrift.ttypes import InvalidPeselException, ClientExistsException

DEFAULT_HOST, DEFAULT_PORT = 'localhost', 1919

socket = TSocket.TSocket(host=DEFAULT_HOST, port=DEFAULT_PORT)
# todo - what is this
transport = TTransport.TBufferedTransport(socket)
transport.open()
protocol = TBinaryProtocol.TBinaryProtocol(transport)

registration_service = RegistrationManager.Client(TMultiplexedProtocol.TMultiplexedProtocol(protocol, 'registration'))
standard_service = StandardAccountManager.Client(TMultiplexedProtocol.TMultiplexedProtocol(protocol, 'standard'))
premium_service = PremiumAccountManager.Client(TMultiplexedProtocol.TMultiplexedProtocol(protocol, 'premium'))


def register_client(name, surname, pesel, salary, currency):
    person = Person(name=name, surname=surname, pesel=pesel, income=salary,
                    baseCurrency=Currency._NAMES_TO_VALUES[currency])
    try:
        client = registration_service.registerClient(person)
    except InvalidPeselException as e:
        print("ERROR Cannot register client with pesel {}. Reason: {}".format(e.pesel, e.reason))
        return None, None
    except ClientExistsException as e:
        print("ERROR Cannot register client with generated guid {} - already exists in db".format(e.guid))
        return None, None
    return client, client.guid


def client_data_for_guid(guid):
    try:
        return standard_service.getClientForGuid(guid=guid)
    except ClientDoesNotExistException as e:
        print("ERROR Client with guid {} does not exist.".format(e.guid))
        return None


def loan_conditions(guid, amount, currency):
    try:
        return premium_service.getLoanConditions(
            inquiry=LoanInquiry(guid=guid, amount=amount, currency=Currency._NAMES_TO_VALUES[currency]))
    except ClientDoesNotExistException as e:
        print("ERROR Client with guid {} does not exist.".format(e.guid))
        return None
    except InvalidAccountTypeException as e:
        print("ERROR Client with guid {} - {}".format(e.guid, e.reason))
        return None


def run():
    print("""Available operations: r, d, l, q
        r NAME SURNAME PESEL SALARY CURRENCY - register new account
        d GUID - check account's data
        l GUID AMOUNT CURRENCY- check loan conditions""")
    print("Available currencies: {}".format(list(Currency._VALUES_TO_NAMES.values())))

    is_running = True
    while is_running:
        command = input("client>")
        parts = command.split()
        op = parts[0]

        if op == "r":
            try:
                [_, name, surname, pesel, salary, currency] = parts
                (client, guid) = register_client(name, surname, pesel, float(salary), currency)
                if client:
                    print("Registered {}-type account for {} {} with guid {}".format(
                        AccountType._VALUES_TO_NAMES[client.type], client.owner.name,
                        client.owner.surname, guid))
            except ValueError:
                print("Invalid register command")
        elif op == "d":
            try:
                [_, guid] = parts
                data = client_data_for_guid(int(guid))
                if data:
                    result = "Guid: {}\nData:\n".format(int(guid))
                    params = [data.owner.name, data.owner.surname, data.owner.pesel,
                              AccountType._VALUES_TO_NAMES[data.type]]
                    result += "\n".join([str(p) for p in params])
                    print(result)
            except ValueError:
                print("Invalid data command")
        elif op == "l":
            try:
                [_, guid, amount, currency] = parts
                conditions = loan_conditions(int(guid), float(amount), currency)
                if conditions:
                    print("Guid: {}\nConditions: base currency: {}\t requested currency: {}".format(int(guid),
                                                                                                    conditions.baseCurrencyCost,
                                                                                                    conditions.requestedCurrencyCost))
            except ValueError:
                print("Invalid loan command")
        elif op == "q":
            print("Exitting...")
            is_running = False
        else:
            print("Invalid command")


if __name__ == '__main__':
    run()
