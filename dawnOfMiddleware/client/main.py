def register_client(name, surname, pesel, salary, currency):
    # todo
    # do sth
    # return (Person, guid)
    return "xD", 1


def client_data_for_guid(guid):
    # todo
    # get balance
    return 1919


def loan_conditions_for_guid(guid):
    # todo
    if guid == 5:
        return None
    else:
        return "xD"


def run(host, port):
    currencies = ['usd', 'pln']

    print("""Available operations: register, balance, loan, quit
        register NAME SURNAME PESEL SALARY CURRENCY- register new account
        balance GUID-check account's balance
        loan GUID - check loan conditions""")
    print("Available currencies: {}".format(currencies))

    # todo - create connection
    is_running = True
    while is_running:
        command = input("client>")
        parts = command.split()
        op = parts[0]

        if op == "register":
            try:
                [_, name, surname, pesel, salary, currency] = parts
                print("""Registering {} {} with:\nPesel: {}\nSalary: {}\nCurrency: {}\n""".format(name, surname,
                                                                                                  int(pesel),
                                                                                                  float(salary),
                                                                                                  currency))
                (client, guid) = register_client(name, surname, pesel, salary, currency)
                if client:
                    print("Registered {} with guid {}".format(client, guid))
            except ValueError:
                print("Invalid register command")
        elif op == "balance":
            try:
                [_, guid] = parts
                balance = client_data_for_guid(guid)
                print("Guid: {}\nBalance: {}".format(int(guid), balance))
            except ValueError:
                print("Invalid balance command")
        elif op == "loan":
            try:
                [_, guid] = parts
                conditions = loan_conditions_for_guid(guid)
                print("Guid: {}\nConditions: {}".format(int(guid), conditions))
            except ValueError:
                print("Invalid loan command")
        elif op == "quit":
            print("Exitting...")
            is_running = False
        else:
            print("Invalid command")


if __name__ == '__main__':
    run(host="localhost", port="1919")
