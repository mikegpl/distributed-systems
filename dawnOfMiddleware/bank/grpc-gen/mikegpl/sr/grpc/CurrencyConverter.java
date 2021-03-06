// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: currency.proto

package mikegpl.sr.grpc;

/**
 * Protobuf type {@code CurrencyConverter}
 */
public  final class CurrencyConverter extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CurrencyConverter)
    CurrencyConverterOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CurrencyConverter.newBuilder() to construct.
  private CurrencyConverter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CurrencyConverter() {
    name_ = 0;
    rate_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CurrencyConverter(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            int rawValue = input.readEnum();

            name_ = rawValue;
            break;
          }
          case 17: {

            rate_ = input.readDouble();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return mikegpl.sr.grpc.CurrencyProto.internal_static_CurrencyConverter_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return mikegpl.sr.grpc.CurrencyProto.internal_static_CurrencyConverter_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            mikegpl.sr.grpc.CurrencyConverter.class, mikegpl.sr.grpc.CurrencyConverter.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private int name_;
  /**
   * <code>.Currency name = 1;</code>
   */
  public int getNameValue() {
    return name_;
  }
  /**
   * <code>.Currency name = 1;</code>
   */
  public mikegpl.sr.grpc.Currency getName() {
    mikegpl.sr.grpc.Currency result = mikegpl.sr.grpc.Currency.valueOf(name_);
    return result == null ? mikegpl.sr.grpc.Currency.UNRECOGNIZED : result;
  }

  public static final int RATE_FIELD_NUMBER = 2;
  private double rate_;
  /**
   * <code>double rate = 2;</code>
   */
  public double getRate() {
    return rate_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (name_ != mikegpl.sr.grpc.Currency.PLN.getNumber()) {
      output.writeEnum(1, name_);
    }
    if (rate_ != 0D) {
      output.writeDouble(2, rate_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (name_ != mikegpl.sr.grpc.Currency.PLN.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(1, name_);
    }
    if (rate_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, rate_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof mikegpl.sr.grpc.CurrencyConverter)) {
      return super.equals(obj);
    }
    mikegpl.sr.grpc.CurrencyConverter other = (mikegpl.sr.grpc.CurrencyConverter) obj;

    boolean result = true;
    result = result && name_ == other.name_;
    result = result && (
        java.lang.Double.doubleToLongBits(getRate())
        == java.lang.Double.doubleToLongBits(
            other.getRate()));
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + name_;
    hash = (37 * hash) + RATE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getRate()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static mikegpl.sr.grpc.CurrencyConverter parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(mikegpl.sr.grpc.CurrencyConverter prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code CurrencyConverter}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CurrencyConverter)
      mikegpl.sr.grpc.CurrencyConverterOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return mikegpl.sr.grpc.CurrencyProto.internal_static_CurrencyConverter_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return mikegpl.sr.grpc.CurrencyProto.internal_static_CurrencyConverter_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              mikegpl.sr.grpc.CurrencyConverter.class, mikegpl.sr.grpc.CurrencyConverter.Builder.class);
    }

    // Construct using mikegpl.sr.grpc.CurrencyConverter.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      name_ = 0;

      rate_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return mikegpl.sr.grpc.CurrencyProto.internal_static_CurrencyConverter_descriptor;
    }

    public mikegpl.sr.grpc.CurrencyConverter getDefaultInstanceForType() {
      return mikegpl.sr.grpc.CurrencyConverter.getDefaultInstance();
    }

    public mikegpl.sr.grpc.CurrencyConverter build() {
      mikegpl.sr.grpc.CurrencyConverter result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public mikegpl.sr.grpc.CurrencyConverter buildPartial() {
      mikegpl.sr.grpc.CurrencyConverter result = new mikegpl.sr.grpc.CurrencyConverter(this);
      result.name_ = name_;
      result.rate_ = rate_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof mikegpl.sr.grpc.CurrencyConverter) {
        return mergeFrom((mikegpl.sr.grpc.CurrencyConverter)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(mikegpl.sr.grpc.CurrencyConverter other) {
      if (other == mikegpl.sr.grpc.CurrencyConverter.getDefaultInstance()) return this;
      if (other.name_ != 0) {
        setNameValue(other.getNameValue());
      }
      if (other.getRate() != 0D) {
        setRate(other.getRate());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      mikegpl.sr.grpc.CurrencyConverter parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (mikegpl.sr.grpc.CurrencyConverter) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int name_ = 0;
    /**
     * <code>.Currency name = 1;</code>
     */
    public int getNameValue() {
      return name_;
    }
    /**
     * <code>.Currency name = 1;</code>
     */
    public Builder setNameValue(int value) {
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.Currency name = 1;</code>
     */
    public mikegpl.sr.grpc.Currency getName() {
      mikegpl.sr.grpc.Currency result = mikegpl.sr.grpc.Currency.valueOf(name_);
      return result == null ? mikegpl.sr.grpc.Currency.UNRECOGNIZED : result;
    }
    /**
     * <code>.Currency name = 1;</code>
     */
    public Builder setName(mikegpl.sr.grpc.Currency value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      name_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.Currency name = 1;</code>
     */
    public Builder clearName() {
      
      name_ = 0;
      onChanged();
      return this;
    }

    private double rate_ ;
    /**
     * <code>double rate = 2;</code>
     */
    public double getRate() {
      return rate_;
    }
    /**
     * <code>double rate = 2;</code>
     */
    public Builder setRate(double value) {
      
      rate_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double rate = 2;</code>
     */
    public Builder clearRate() {
      
      rate_ = 0D;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:CurrencyConverter)
  }

  // @@protoc_insertion_point(class_scope:CurrencyConverter)
  private static final mikegpl.sr.grpc.CurrencyConverter DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new mikegpl.sr.grpc.CurrencyConverter();
  }

  public static mikegpl.sr.grpc.CurrencyConverter getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CurrencyConverter>
      PARSER = new com.google.protobuf.AbstractParser<CurrencyConverter>() {
    public CurrencyConverter parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CurrencyConverter(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CurrencyConverter> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CurrencyConverter> getParserForType() {
    return PARSER;
  }

  public mikegpl.sr.grpc.CurrencyConverter getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

