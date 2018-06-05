package mikegpl.sr.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0-SNAPSHOT)",
    comments = "Source: currency.proto")
public final class CurrencyServiceGrpc {

  private CurrencyServiceGrpc() {}

  public static final String SERVICE_NAME = "CurrencyService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getCurrentConvertersMethod()} instead. 
  public static final io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.ConvertersState> METHOD_CURRENT_CONVERTERS = getCurrentConvertersMethodHelper();

  private static volatile io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.ConvertersState> getCurrentConvertersMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.ConvertersState> getCurrentConvertersMethod() {
    return getCurrentConvertersMethodHelper();
  }

  private static io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.ConvertersState> getCurrentConvertersMethodHelper() {
    io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest, mikegpl.sr.grpc.ConvertersState> getCurrentConvertersMethod;
    if ((getCurrentConvertersMethod = CurrencyServiceGrpc.getCurrentConvertersMethod) == null) {
      synchronized (CurrencyServiceGrpc.class) {
        if ((getCurrentConvertersMethod = CurrencyServiceGrpc.getCurrentConvertersMethod) == null) {
          CurrencyServiceGrpc.getCurrentConvertersMethod = getCurrentConvertersMethod = 
              io.grpc.MethodDescriptor.<mikegpl.sr.grpc.CurrencyStateRequest, mikegpl.sr.grpc.ConvertersState>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "CurrencyService", "CurrentConverters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mikegpl.sr.grpc.CurrencyStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mikegpl.sr.grpc.ConvertersState.getDefaultInstance()))
                  .setSchemaDescriptor(new CurrencyServiceMethodDescriptorSupplier("CurrentConverters"))
                  .build();
          }
        }
     }
     return getCurrentConvertersMethod;
  }
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  @java.lang.Deprecated // Use {@link #getStreamConvertersMethod()} instead. 
  public static final io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.CurrencyConverter> METHOD_STREAM_CONVERTERS = getStreamConvertersMethodHelper();

  private static volatile io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.CurrencyConverter> getStreamConvertersMethod;

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.CurrencyConverter> getStreamConvertersMethod() {
    return getStreamConvertersMethodHelper();
  }

  private static io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest,
      mikegpl.sr.grpc.CurrencyConverter> getStreamConvertersMethodHelper() {
    io.grpc.MethodDescriptor<mikegpl.sr.grpc.CurrencyStateRequest, mikegpl.sr.grpc.CurrencyConverter> getStreamConvertersMethod;
    if ((getStreamConvertersMethod = CurrencyServiceGrpc.getStreamConvertersMethod) == null) {
      synchronized (CurrencyServiceGrpc.class) {
        if ((getStreamConvertersMethod = CurrencyServiceGrpc.getStreamConvertersMethod) == null) {
          CurrencyServiceGrpc.getStreamConvertersMethod = getStreamConvertersMethod = 
              io.grpc.MethodDescriptor.<mikegpl.sr.grpc.CurrencyStateRequest, mikegpl.sr.grpc.CurrencyConverter>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "CurrencyService", "StreamConverters"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mikegpl.sr.grpc.CurrencyStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mikegpl.sr.grpc.CurrencyConverter.getDefaultInstance()))
                  .setSchemaDescriptor(new CurrencyServiceMethodDescriptorSupplier("StreamConverters"))
                  .build();
          }
        }
     }
     return getStreamConvertersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CurrencyServiceStub newStub(io.grpc.Channel channel) {
    return new CurrencyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CurrencyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new CurrencyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CurrencyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new CurrencyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class CurrencyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void currentConverters(mikegpl.sr.grpc.CurrencyStateRequest request,
        io.grpc.stub.StreamObserver<mikegpl.sr.grpc.ConvertersState> responseObserver) {
      asyncUnimplementedUnaryCall(getCurrentConvertersMethodHelper(), responseObserver);
    }

    /**
     */
    public void streamConverters(mikegpl.sr.grpc.CurrencyStateRequest request,
        io.grpc.stub.StreamObserver<mikegpl.sr.grpc.CurrencyConverter> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamConvertersMethodHelper(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCurrentConvertersMethodHelper(),
            asyncUnaryCall(
              new MethodHandlers<
                mikegpl.sr.grpc.CurrencyStateRequest,
                mikegpl.sr.grpc.ConvertersState>(
                  this, METHODID_CURRENT_CONVERTERS)))
          .addMethod(
            getStreamConvertersMethodHelper(),
            asyncServerStreamingCall(
              new MethodHandlers<
                mikegpl.sr.grpc.CurrencyStateRequest,
                mikegpl.sr.grpc.CurrencyConverter>(
                  this, METHODID_STREAM_CONVERTERS)))
          .build();
    }
  }

  /**
   */
  public static final class CurrencyServiceStub extends io.grpc.stub.AbstractStub<CurrencyServiceStub> {
    private CurrencyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CurrencyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyServiceStub(channel, callOptions);
    }

    /**
     */
    public void currentConverters(mikegpl.sr.grpc.CurrencyStateRequest request,
        io.grpc.stub.StreamObserver<mikegpl.sr.grpc.ConvertersState> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCurrentConvertersMethodHelper(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void streamConverters(mikegpl.sr.grpc.CurrencyStateRequest request,
        io.grpc.stub.StreamObserver<mikegpl.sr.grpc.CurrencyConverter> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamConvertersMethodHelper(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CurrencyServiceBlockingStub extends io.grpc.stub.AbstractStub<CurrencyServiceBlockingStub> {
    private CurrencyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CurrencyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public mikegpl.sr.grpc.ConvertersState currentConverters(mikegpl.sr.grpc.CurrencyStateRequest request) {
      return blockingUnaryCall(
          getChannel(), getCurrentConvertersMethodHelper(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<mikegpl.sr.grpc.CurrencyConverter> streamConverters(
        mikegpl.sr.grpc.CurrencyStateRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamConvertersMethodHelper(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CurrencyServiceFutureStub extends io.grpc.stub.AbstractStub<CurrencyServiceFutureStub> {
    private CurrencyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private CurrencyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CurrencyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new CurrencyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mikegpl.sr.grpc.ConvertersState> currentConverters(
        mikegpl.sr.grpc.CurrencyStateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCurrentConvertersMethodHelper(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CURRENT_CONVERTERS = 0;
  private static final int METHODID_STREAM_CONVERTERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CurrencyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CurrencyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CURRENT_CONVERTERS:
          serviceImpl.currentConverters((mikegpl.sr.grpc.CurrencyStateRequest) request,
              (io.grpc.stub.StreamObserver<mikegpl.sr.grpc.ConvertersState>) responseObserver);
          break;
        case METHODID_STREAM_CONVERTERS:
          serviceImpl.streamConverters((mikegpl.sr.grpc.CurrencyStateRequest) request,
              (io.grpc.stub.StreamObserver<mikegpl.sr.grpc.CurrencyConverter>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CurrencyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CurrencyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return mikegpl.sr.grpc.CurrencyProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CurrencyService");
    }
  }

  private static final class CurrencyServiceFileDescriptorSupplier
      extends CurrencyServiceBaseDescriptorSupplier {
    CurrencyServiceFileDescriptorSupplier() {}
  }

  private static final class CurrencyServiceMethodDescriptorSupplier
      extends CurrencyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CurrencyServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CurrencyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CurrencyServiceFileDescriptorSupplier())
              .addMethod(getCurrentConvertersMethodHelper())
              .addMethod(getStreamConvertersMethodHelper())
              .build();
        }
      }
    }
    return result;
  }
}
