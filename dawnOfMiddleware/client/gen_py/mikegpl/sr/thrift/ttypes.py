#
# Autogenerated by Thrift Compiler (0.11.0)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TFrozenDict, TException, TApplicationException
from thrift.protocol.TProtocol import TProtocolException
from thrift.TRecursive import fix_spec

import sys

from thrift.transport import TTransport
all_structs = []


class Currency(object):
    PLN = 0
    USD = 1
    EUR = 2
    VEF = 3

    _VALUES_TO_NAMES = {
        0: "PLN",
        1: "USD",
        2: "EUR",
        3: "VEF",
    }

    _NAMES_TO_VALUES = {
        "PLN": 0,
        "USD": 1,
        "EUR": 2,
        "VEF": 3,
    }


class AccountType(object):
    STANDARD = 0
    PREMIUM = 1

    _VALUES_TO_NAMES = {
        0: "STANDARD",
        1: "PREMIUM",
    }

    _NAMES_TO_VALUES = {
        "STANDARD": 0,
        "PREMIUM": 1,
    }


class Person(object):
    """
    Attributes:
     - name
     - surname
     - pesel
     - income
     - baseCurrency
    """


    def __init__(self, name=None, surname=None, pesel=None, income=None, baseCurrency=None,):
        self.name = name
        self.surname = surname
        self.pesel = pesel
        self.income = income
        self.baseCurrency = baseCurrency

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRING:
                    self.name = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRING:
                    self.surname = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.STRING:
                    self.pesel = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 4:
                if ftype == TType.DOUBLE:
                    self.income = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            elif fid == 5:
                if ftype == TType.I32:
                    self.baseCurrency = iprot.readI32()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('Person')
        if self.name is not None:
            oprot.writeFieldBegin('name', TType.STRING, 1)
            oprot.writeString(self.name.encode('utf-8') if sys.version_info[0] == 2 else self.name)
            oprot.writeFieldEnd()
        if self.surname is not None:
            oprot.writeFieldBegin('surname', TType.STRING, 2)
            oprot.writeString(self.surname.encode('utf-8') if sys.version_info[0] == 2 else self.surname)
            oprot.writeFieldEnd()
        if self.pesel is not None:
            oprot.writeFieldBegin('pesel', TType.STRING, 3)
            oprot.writeString(self.pesel.encode('utf-8') if sys.version_info[0] == 2 else self.pesel)
            oprot.writeFieldEnd()
        if self.income is not None:
            oprot.writeFieldBegin('income', TType.DOUBLE, 4)
            oprot.writeDouble(self.income)
            oprot.writeFieldEnd()
        if self.baseCurrency is not None:
            oprot.writeFieldBegin('baseCurrency', TType.I32, 5)
            oprot.writeI32(self.baseCurrency)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class BankClient(object):
    """
    Attributes:
     - owner
     - type
     - guid
    """


    def __init__(self, owner=None, type=None, guid=None,):
        self.owner = owner
        self.type = type
        self.guid = guid

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRUCT:
                    self.owner = Person()
                    self.owner.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.I32:
                    self.type = iprot.readI32()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.I64:
                    self.guid = iprot.readI64()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('BankClient')
        if self.owner is not None:
            oprot.writeFieldBegin('owner', TType.STRUCT, 1)
            self.owner.write(oprot)
            oprot.writeFieldEnd()
        if self.type is not None:
            oprot.writeFieldBegin('type', TType.I32, 2)
            oprot.writeI32(self.type)
            oprot.writeFieldEnd()
        if self.guid is not None:
            oprot.writeFieldBegin('guid', TType.I64, 3)
            oprot.writeI64(self.guid)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class LoanInquiry(object):
    """
    Attributes:
     - guid
     - amount
     - currency
    """


    def __init__(self, guid=None, amount=None, currency=None,):
        self.guid = guid
        self.amount = amount
        self.currency = currency

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I64:
                    self.guid = iprot.readI64()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.DOUBLE:
                    self.amount = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            elif fid == 3:
                if ftype == TType.I32:
                    self.currency = iprot.readI32()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('LoanInquiry')
        if self.guid is not None:
            oprot.writeFieldBegin('guid', TType.I64, 1)
            oprot.writeI64(self.guid)
            oprot.writeFieldEnd()
        if self.amount is not None:
            oprot.writeFieldBegin('amount', TType.DOUBLE, 2)
            oprot.writeDouble(self.amount)
            oprot.writeFieldEnd()
        if self.currency is not None:
            oprot.writeFieldBegin('currency', TType.I32, 3)
            oprot.writeI32(self.currency)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class LoanOffer(object):
    """
    Attributes:
     - baseCurrencyCost
     - requestedCurrencyCost
    """


    def __init__(self, baseCurrencyCost=None, requestedCurrencyCost=None,):
        self.baseCurrencyCost = baseCurrencyCost
        self.requestedCurrencyCost = requestedCurrencyCost

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.DOUBLE:
                    self.baseCurrencyCost = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.DOUBLE:
                    self.requestedCurrencyCost = iprot.readDouble()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('LoanOffer')
        if self.baseCurrencyCost is not None:
            oprot.writeFieldBegin('baseCurrencyCost', TType.DOUBLE, 1)
            oprot.writeDouble(self.baseCurrencyCost)
            oprot.writeFieldEnd()
        if self.requestedCurrencyCost is not None:
            oprot.writeFieldBegin('requestedCurrencyCost', TType.DOUBLE, 2)
            oprot.writeDouble(self.requestedCurrencyCost)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class InvalidPeselException(TException):
    """
    Attributes:
     - pesel
     - reason
    """


    def __init__(self, pesel=None, reason=None,):
        self.pesel = pesel
        self.reason = reason

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.STRING:
                    self.pesel = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRING:
                    self.reason = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('InvalidPeselException')
        if self.pesel is not None:
            oprot.writeFieldBegin('pesel', TType.STRING, 1)
            oprot.writeString(self.pesel.encode('utf-8') if sys.version_info[0] == 2 else self.pesel)
            oprot.writeFieldEnd()
        if self.reason is not None:
            oprot.writeFieldBegin('reason', TType.STRING, 2)
            oprot.writeString(self.reason.encode('utf-8') if sys.version_info[0] == 2 else self.reason)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __str__(self):
        return repr(self)

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class ClientExistsException(TException):
    """
    Attributes:
     - guid
    """


    def __init__(self, guid=None,):
        self.guid = guid

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I64:
                    self.guid = iprot.readI64()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('ClientExistsException')
        if self.guid is not None:
            oprot.writeFieldBegin('guid', TType.I64, 1)
            oprot.writeI64(self.guid)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __str__(self):
        return repr(self)

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class ClientDoesNotExistException(TException):
    """
    Attributes:
     - guid
    """


    def __init__(self, guid=None,):
        self.guid = guid

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I64:
                    self.guid = iprot.readI64()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('ClientDoesNotExistException')
        if self.guid is not None:
            oprot.writeFieldBegin('guid', TType.I64, 1)
            oprot.writeI64(self.guid)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __str__(self):
        return repr(self)

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)


class InvalidAccountTypeException(TException):
    """
    Attributes:
     - guid
     - reason
    """


    def __init__(self, guid=None, reason=None,):
        self.guid = guid
        self.reason = reason

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 1:
                if ftype == TType.I64:
                    self.guid = iprot.readI64()
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRING:
                    self.reason = iprot.readString().decode('utf-8') if sys.version_info[0] == 2 else iprot.readString()
                else:
                    iprot.skip(ftype)
            else:
                iprot.skip(ftype)
            iprot.readFieldEnd()
        iprot.readStructEnd()

    def write(self, oprot):
        if oprot._fast_encode is not None and self.thrift_spec is not None:
            oprot.trans.write(oprot._fast_encode(self, [self.__class__, self.thrift_spec]))
            return
        oprot.writeStructBegin('InvalidAccountTypeException')
        if self.guid is not None:
            oprot.writeFieldBegin('guid', TType.I64, 1)
            oprot.writeI64(self.guid)
            oprot.writeFieldEnd()
        if self.reason is not None:
            oprot.writeFieldBegin('reason', TType.STRING, 2)
            oprot.writeString(self.reason.encode('utf-8') if sys.version_info[0] == 2 else self.reason)
            oprot.writeFieldEnd()
        oprot.writeFieldStop()
        oprot.writeStructEnd()

    def validate(self):
        return

    def __str__(self):
        return repr(self)

    def __repr__(self):
        L = ['%s=%r' % (key, value)
             for key, value in self.__dict__.items()]
        return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

    def __eq__(self, other):
        return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

    def __ne__(self, other):
        return not (self == other)
all_structs.append(Person)
Person.thrift_spec = (
    None,  # 0
    (1, TType.STRING, 'name', 'UTF8', None, ),  # 1
    (2, TType.STRING, 'surname', 'UTF8', None, ),  # 2
    (3, TType.STRING, 'pesel', 'UTF8', None, ),  # 3
    (4, TType.DOUBLE, 'income', None, None, ),  # 4
    (5, TType.I32, 'baseCurrency', None, None, ),  # 5
)
all_structs.append(BankClient)
BankClient.thrift_spec = (
    None,  # 0
    (1, TType.STRUCT, 'owner', [Person, None], None, ),  # 1
    (2, TType.I32, 'type', None, None, ),  # 2
    (3, TType.I64, 'guid', None, None, ),  # 3
)
all_structs.append(LoanInquiry)
LoanInquiry.thrift_spec = (
    None,  # 0
    (1, TType.I64, 'guid', None, None, ),  # 1
    (2, TType.DOUBLE, 'amount', None, None, ),  # 2
    (3, TType.I32, 'currency', None, None, ),  # 3
)
all_structs.append(LoanOffer)
LoanOffer.thrift_spec = (
    None,  # 0
    (1, TType.DOUBLE, 'baseCurrencyCost', None, None, ),  # 1
    (2, TType.DOUBLE, 'requestedCurrencyCost', None, None, ),  # 2
)
all_structs.append(InvalidPeselException)
InvalidPeselException.thrift_spec = (
    None,  # 0
    (1, TType.STRING, 'pesel', 'UTF8', None, ),  # 1
    (2, TType.STRING, 'reason', 'UTF8', None, ),  # 2
)
all_structs.append(ClientExistsException)
ClientExistsException.thrift_spec = (
    None,  # 0
    (1, TType.I64, 'guid', None, None, ),  # 1
)
all_structs.append(ClientDoesNotExistException)
ClientDoesNotExistException.thrift_spec = (
    None,  # 0
    (1, TType.I64, 'guid', None, None, ),  # 1
)
all_structs.append(InvalidAccountTypeException)
InvalidAccountTypeException.thrift_spec = (
    None,  # 0
    (1, TType.I64, 'guid', None, None, ),  # 1
    (2, TType.STRING, 'reason', 'UTF8', None, ),  # 2
)
fix_spec(all_structs)
del all_structs
