import sys

from thrift.protocol import TBinaryProtocol, TMultiplexedProtocol
from thrift.transport import TSocket, TTransport

sys.path.append('gen_py')

# services
from gen_py.mikegpl.sr.thrift import RegistrationManager, StandardAccountManager, PremiumAccountManager
# types
from gen_py.mikegpl.sr.thrift.ttypes import Currency, Person
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
    return client, client.guid


def client_data_for_guid(guid):
    try:
        return standard_service.getClientForGuid(guid=guid)
    except ClientExistsException as e:
        print("ERROR Invalid gui {}. Reason: {}".format(e.guid, e.reason))
        return None


def loan_conditions_for_guid(guid):
    try:
        return premium_service.getLoanConditionsForGuid(guid=guid)
    except ClientExistsException as e:
        print("ERROR Invalid gui {}. Reason: {}".format(e.guid, e.reason))
        return None


def run():
    print("""Available operations: register, data, loan, quit
        register NAME SURNAME PESEL SALARY CURRENCY - register new account
        data GUID - check account's data
        loan GUID - check loan conditions""")
    print("Available currencies: {}".format(list(Currency._VALUES_TO_NAMES.values())))

    is_running = True
    while is_running:
        command = input("client>")
        parts = command.split()
        op = parts[0]

        if op == "register":
            try:
                [_, name, surname, pesel, salary, currency] = parts
                print("""Registering {} {} with:\nPesel: {}\nSalary: {}\nCurrency: {}""".format(name, surname,
                                                                                                int(pesel),
                                                                                                float(salary),
                                                                                                currency))
                (client, guid) = register_client(name, surname, pesel, float(salary), currency)
                if client:
                    print("Registered {} with guid {}".format(client, guid))
            except ValueError:
                print("Invalid register command")
        elif op == "data":
            try:
                [_, guid] = parts
                data = client_data_for_guid(int(guid))
                if data:
                    print("Guid: {}\nData: {}".format(int(guid), data))
            except ValueError:
                print("Invalid data command")
        elif op == "loan":
            try:
                [_, guid] = parts
                conditions = loan_conditions_for_guid(int(guid))
                print("Guid: {}\nConditions: {}".format(int(guid), conditions))
            except ValueError:
                print("Invalid loan command")
        elif op == "quit":
            print("Exitting...")
            is_running = False
        else:
            print("Invalid command")


if __name__ == '__main__':
    run()
