// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: currency.proto

package mikegpl.sr.grpc;

/**
 * Protobuf type {@code ConvertersState}
 */
public  final class ConvertersState extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ConvertersState)
    ConvertersStateOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ConvertersState.newBuilder() to construct.
  private ConvertersState(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ConvertersState() {
    converter_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ConvertersState(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              converter_ = new java.util.ArrayList<mikegpl.sr.grpc.CurrencyConverter>();
              mutable_bitField0_ |= 0x00000001;
            }
            converter_.add(
                input.readMessage(mikegpl.sr.grpc.CurrencyConverter.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        converter_ = java.util.Collections.unmodifiableList(converter_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return mikegpl.sr.grpc.CurrencyProto.internal_static_ConvertersState_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return mikegpl.sr.grpc.CurrencyProto.internal_static_ConvertersState_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            mikegpl.sr.grpc.ConvertersState.class, mikegpl.sr.grpc.ConvertersState.Builder.class);
  }

  public static final int CONVERTER_FIELD_NUMBER = 1;
  private java.util.List<mikegpl.sr.grpc.CurrencyConverter> converter_;
  /**
   * <code>repeated .CurrencyConverter converter = 1;</code>
   */
  public java.util.List<mikegpl.sr.grpc.CurrencyConverter> getConverterList() {
    return converter_;
  }
  /**
   * <code>repeated .CurrencyConverter converter = 1;</code>
   */
  public java.util.List<? extends mikegpl.sr.grpc.CurrencyConverterOrBuilder> 
      getConverterOrBuilderList() {
    return converter_;
  }
  /**
   * <code>repeated .CurrencyConverter converter = 1;</code>
   */
  public int getConverterCount() {
    return converter_.size();
  }
  /**
   * <code>repeated .CurrencyConverter converter = 1;</code>
   */
  public mikegpl.sr.grpc.CurrencyConverter getConverter(int index) {
    return converter_.get(index);
  }
  /**
   * <code>repeated .CurrencyConverter converter = 1;</code>
   */
  public mikegpl.sr.grpc.CurrencyConverterOrBuilder getConverterOrBuilder(
      int index) {
    return converter_.get(index);
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
    for (int i = 0; i < converter_.size(); i++) {
      output.writeMessage(1, converter_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < converter_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, converter_.get(i));
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
    if (!(obj instanceof mikegpl.sr.grpc.ConvertersState)) {
      return super.equals(obj);
    }
    mikegpl.sr.grpc.ConvertersState other = (mikegpl.sr.grpc.ConvertersState) obj;

    boolean result = true;
    result = result && getConverterList()
        .equals(other.getConverterList());
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
    if (getConverterCount() > 0) {
      hash = (37 * hash) + CONVERTER_FIELD_NUMBER;
      hash = (53 * hash) + getConverterList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static mikegpl.sr.grpc.ConvertersState parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static mikegpl.sr.grpc.ConvertersState parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static mikegpl.sr.grpc.ConvertersState parseFrom(
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
  public static Builder newBuilder(mikegpl.sr.grpc.ConvertersState prototype) {
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
   * Protobuf type {@code ConvertersState}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ConvertersState)
      mikegpl.sr.grpc.ConvertersStateOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return mikegpl.sr.grpc.CurrencyProto.internal_static_ConvertersState_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return mikegpl.sr.grpc.CurrencyProto.internal_static_ConvertersState_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              mikegpl.sr.grpc.ConvertersState.class, mikegpl.sr.grpc.ConvertersState.Builder.class);
    }

    // Construct using mikegpl.sr.grpc.ConvertersState.newBuilder()
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
        getConverterFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (converterBuilder_ == null) {
        converter_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        converterBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return mikegpl.sr.grpc.CurrencyProto.internal_static_ConvertersState_descriptor;
    }

    public mikegpl.sr.grpc.ConvertersState getDefaultInstanceForType() {
      return mikegpl.sr.grpc.ConvertersState.getDefaultInstance();
    }

    public mikegpl.sr.grpc.ConvertersState build() {
      mikegpl.sr.grpc.ConvertersState result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public mikegpl.sr.grpc.ConvertersState buildPartial() {
      mikegpl.sr.grpc.ConvertersState result = new mikegpl.sr.grpc.ConvertersState(this);
      int from_bitField0_ = bitField0_;
      if (converterBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          converter_ = java.util.Collections.unmodifiableList(converter_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.converter_ = converter_;
      } else {
        result.converter_ = converterBuilder_.build();
      }
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
      if (other instanceof mikegpl.sr.grpc.ConvertersState) {
        return mergeFrom((mikegpl.sr.grpc.ConvertersState)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(mikegpl.sr.grpc.ConvertersState other) {
      if (other == mikegpl.sr.grpc.ConvertersState.getDefaultInstance()) return this;
      if (converterBuilder_ == null) {
        if (!other.converter_.isEmpty()) {
          if (converter_.isEmpty()) {
            converter_ = other.converter_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureConverterIsMutable();
            converter_.addAll(other.converter_);
          }
          onChanged();
        }
      } else {
        if (!other.converter_.isEmpty()) {
          if (converterBuilder_.isEmpty()) {
            converterBuilder_.dispose();
            converterBuilder_ = null;
            converter_ = other.converter_;
            bitField0_ = (bitField0_ & ~0x00000001);
            converterBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getConverterFieldBuilder() : null;
          } else {
            converterBuilder_.addAllMessages(other.converter_);
          }
        }
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
      mikegpl.sr.grpc.ConvertersState parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (mikegpl.sr.grpc.ConvertersState) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<mikegpl.sr.grpc.CurrencyConverter> converter_ =
      java.util.Collections.emptyList();
    private void ensureConverterIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        converter_ = new java.util.ArrayList<mikegpl.sr.grpc.CurrencyConverter>(converter_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        mikegpl.sr.grpc.CurrencyConverter, mikegpl.sr.grpc.CurrencyConverter.Builder, mikegpl.sr.grpc.CurrencyConverterOrBuilder> converterBuilder_;

    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public java.util.List<mikegpl.sr.grpc.CurrencyConverter> getConverterList() {
      if (converterBuilder_ == null) {
        return java.util.Collections.unmodifiableList(converter_);
      } else {
        return converterBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public int getConverterCount() {
      if (converterBuilder_ == null) {
        return converter_.size();
      } else {
        return converterBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public mikegpl.sr.grpc.CurrencyConverter getConverter(int index) {
      if (converterBuilder_ == null) {
        return converter_.get(index);
      } else {
        return converterBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder setConverter(
        int index, mikegpl.sr.grpc.CurrencyConverter value) {
      if (converterBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConverterIsMutable();
        converter_.set(index, value);
        onChanged();
      } else {
        converterBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder setConverter(
        int index, mikegpl.sr.grpc.CurrencyConverter.Builder builderForValue) {
      if (converterBuilder_ == null) {
        ensureConverterIsMutable();
        converter_.set(index, builderForValue.build());
        onChanged();
      } else {
        converterBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder addConverter(mikegpl.sr.grpc.CurrencyConverter value) {
      if (converterBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConverterIsMutable();
        converter_.add(value);
        onChanged();
      } else {
        converterBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder addConverter(
        int index, mikegpl.sr.grpc.CurrencyConverter value) {
      if (converterBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConverterIsMutable();
        converter_.add(index, value);
        onChanged();
      } else {
        converterBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder addConverter(
        mikegpl.sr.grpc.CurrencyConverter.Builder builderForValue) {
      if (converterBuilder_ == null) {
        ensureConverterIsMutable();
        converter_.add(builderForValue.build());
        onChanged();
      } else {
        converterBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder addConverter(
        int index, mikegpl.sr.grpc.CurrencyConverter.Builder builderForValue) {
      if (converterBuilder_ == null) {
        ensureConverterIsMutable();
        converter_.add(index, builderForValue.build());
        onChanged();
      } else {
        converterBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder addAllConverter(
        java.lang.Iterable<? extends mikegpl.sr.grpc.CurrencyConverter> values) {
      if (converterBuilder_ == null) {
        ensureConverterIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, converter_);
        onChanged();
      } else {
        converterBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder clearConverter() {
      if (converterBuilder_ == null) {
        converter_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        converterBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public Builder removeConverter(int index) {
      if (converterBuilder_ == null) {
        ensureConverterIsMutable();
        converter_.remove(index);
        onChanged();
      } else {
        converterBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public mikegpl.sr.grpc.CurrencyConverter.Builder getConverterBuilder(
        int index) {
      return getConverterFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public mikegpl.sr.grpc.CurrencyConverterOrBuilder getConverterOrBuilder(
        int index) {
      if (converterBuilder_ == null) {
        return converter_.get(index);  } else {
        return converterBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public java.util.List<? extends mikegpl.sr.grpc.CurrencyConverterOrBuilder> 
         getConverterOrBuilderList() {
      if (converterBuilder_ != null) {
        return converterBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(converter_);
      }
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public mikegpl.sr.grpc.CurrencyConverter.Builder addConverterBuilder() {
      return getConverterFieldBuilder().addBuilder(
          mikegpl.sr.grpc.CurrencyConverter.getDefaultInstance());
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public mikegpl.sr.grpc.CurrencyConverter.Builder addConverterBuilder(
        int index) {
      return getConverterFieldBuilder().addBuilder(
          index, mikegpl.sr.grpc.CurrencyConverter.getDefaultInstance());
    }
    /**
     * <code>repeated .CurrencyConverter converter = 1;</code>
     */
    public java.util.List<mikegpl.sr.grpc.CurrencyConverter.Builder> 
         getConverterBuilderList() {
      return getConverterFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        mikegpl.sr.grpc.CurrencyConverter, mikegpl.sr.grpc.CurrencyConverter.Builder, mikegpl.sr.grpc.CurrencyConverterOrBuilder> 
        getConverterFieldBuilder() {
      if (converterBuilder_ == null) {
        converterBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            mikegpl.sr.grpc.CurrencyConverter, mikegpl.sr.grpc.CurrencyConverter.Builder, mikegpl.sr.grpc.CurrencyConverterOrBuilder>(
                converter_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        converter_ = null;
      }
      return converterBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ConvertersState)
  }

  // @@protoc_insertion_point(class_scope:ConvertersState)
  private static final mikegpl.sr.grpc.ConvertersState DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new mikegpl.sr.grpc.ConvertersState();
  }

  public static mikegpl.sr.grpc.ConvertersState getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ConvertersState>
      PARSER = new com.google.protobuf.AbstractParser<ConvertersState>() {
    public ConvertersState parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ConvertersState(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ConvertersState> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ConvertersState> getParserForType() {
    return PARSER;
  }

  public mikegpl.sr.grpc.ConvertersState getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

