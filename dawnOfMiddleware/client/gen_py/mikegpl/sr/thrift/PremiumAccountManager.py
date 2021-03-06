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
import mikegpl.sr.thrift.StandardAccountManager
import logging
from .ttypes import *
from thrift.Thrift import TProcessor
from thrift.transport import TTransport
all_structs = []


class Iface(mikegpl.sr.thrift.StandardAccountManager.Iface):
    def getLoanConditions(self, inquiry):
        """
        Parameters:
         - inquiry
        """
        pass


class Client(mikegpl.sr.thrift.StandardAccountManager.Client, Iface):
    def __init__(self, iprot, oprot=None):
        mikegpl.sr.thrift.StandardAccountManager.Client.__init__(self, iprot, oprot)

    def getLoanConditions(self, inquiry):
        """
        Parameters:
         - inquiry
        """
        self.send_getLoanConditions(inquiry)
        return self.recv_getLoanConditions()

    def send_getLoanConditions(self, inquiry):
        self._oprot.writeMessageBegin('getLoanConditions', TMessageType.CALL, self._seqid)
        args = getLoanConditions_args()
        args.inquiry = inquiry
        args.write(self._oprot)
        self._oprot.writeMessageEnd()
        self._oprot.trans.flush()

    def recv_getLoanConditions(self):
        iprot = self._iprot
        (fname, mtype, rseqid) = iprot.readMessageBegin()
        if mtype == TMessageType.EXCEPTION:
            x = TApplicationException()
            x.read(iprot)
            iprot.readMessageEnd()
            raise x
        result = getLoanConditions_result()
        result.read(iprot)
        iprot.readMessageEnd()
        if result.success is not None:
            return result.success
        if result.e1 is not None:
            raise result.e1
        if result.e2 is not None:
            raise result.e2
        raise TApplicationException(TApplicationException.MISSING_RESULT, "getLoanConditions failed: unknown result")


class Processor(mikegpl.sr.thrift.StandardAccountManager.Processor, Iface, TProcessor):
    def __init__(self, handler):
        mikegpl.sr.thrift.StandardAccountManager.Processor.__init__(self, handler)
        self._processMap["getLoanConditions"] = Processor.process_getLoanConditions

    def process(self, iprot, oprot):
        (name, type, seqid) = iprot.readMessageBegin()
        if name not in self._processMap:
            iprot.skip(TType.STRUCT)
            iprot.readMessageEnd()
            x = TApplicationException(TApplicationException.UNKNOWN_METHOD, 'Unknown function %s' % (name))
            oprot.writeMessageBegin(name, TMessageType.EXCEPTION, seqid)
            x.write(oprot)
            oprot.writeMessageEnd()
            oprot.trans.flush()
            return
        else:
            self._processMap[name](self, seqid, iprot, oprot)
        return True

    def process_getLoanConditions(self, seqid, iprot, oprot):
        args = getLoanConditions_args()
        args.read(iprot)
        iprot.readMessageEnd()
        result = getLoanConditions_result()
        try:
            result.success = self._handler.getLoanConditions(args.inquiry)
            msg_type = TMessageType.REPLY
        except TTransport.TTransportException:
            raise
        except ClientDoesNotExistException as e1:
            msg_type = TMessageType.REPLY
            result.e1 = e1
        except InvalidAccountTypeException as e2:
            msg_type = TMessageType.REPLY
            result.e2 = e2
        except TApplicationException as ex:
            logging.exception('TApplication exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = ex
        except Exception:
            logging.exception('Unexpected exception in handler')
            msg_type = TMessageType.EXCEPTION
            result = TApplicationException(TApplicationException.INTERNAL_ERROR, 'Internal error')
        oprot.writeMessageBegin("getLoanConditions", msg_type, seqid)
        result.write(oprot)
        oprot.writeMessageEnd()
        oprot.trans.flush()

# HELPER FUNCTIONS AND STRUCTURES


class getLoanConditions_args(object):
    """
    Attributes:
     - inquiry
    """


    def __init__(self, inquiry=None,):
        self.inquiry = inquiry

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
                    self.inquiry = LoanInquiry()
                    self.inquiry.read(iprot)
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
        oprot.writeStructBegin('getLoanConditions_args')
        if self.inquiry is not None:
            oprot.writeFieldBegin('inquiry', TType.STRUCT, 1)
            self.inquiry.write(oprot)
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
all_structs.append(getLoanConditions_args)
getLoanConditions_args.thrift_spec = (
    None,  # 0
    (1, TType.STRUCT, 'inquiry', [LoanInquiry, None], None, ),  # 1
)


class getLoanConditions_result(object):
    """
    Attributes:
     - success
     - e1
     - e2
    """


    def __init__(self, success=None, e1=None, e2=None,):
        self.success = success
        self.e1 = e1
        self.e2 = e2

    def read(self, iprot):
        if iprot._fast_decode is not None and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None:
            iprot._fast_decode(self, iprot, [self.__class__, self.thrift_spec])
            return
        iprot.readStructBegin()
        while True:
            (fname, ftype, fid) = iprot.readFieldBegin()
            if ftype == TType.STOP:
                break
            if fid == 0:
                if ftype == TType.STRUCT:
                    self.success = LoanOffer()
                    self.success.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 1:
                if ftype == TType.STRUCT:
                    self.e1 = ClientDoesNotExistException()
                    self.e1.read(iprot)
                else:
                    iprot.skip(ftype)
            elif fid == 2:
                if ftype == TType.STRUCT:
                    self.e2 = InvalidAccountTypeException()
                    self.e2.read(iprot)
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
        oprot.writeStructBegin('getLoanConditions_result')
        if self.success is not None:
            oprot.writeFieldBegin('success', TType.STRUCT, 0)
            self.success.write(oprot)
            oprot.writeFieldEnd()
        if self.e1 is not None:
            oprot.writeFieldBegin('e1', TType.STRUCT, 1)
            self.e1.write(oprot)
            oprot.writeFieldEnd()
        if self.e2 is not None:
            oprot.writeFieldBegin('e2', TType.STRUCT, 2)
            self.e2.write(oprot)
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
all_structs.append(getLoanConditions_result)
getLoanConditions_result.thrift_spec = (
    (0, TType.STRUCT, 'success', [LoanOffer, None], None, ),  # 0
    (1, TType.STRUCT, 'e1', [ClientDoesNotExistException, None], None, ),  # 1
    (2, TType.STRUCT, 'e2', [InvalidAccountTypeException, None], None, ),  # 2
)
fix_spec(all_structs)
del all_structs

