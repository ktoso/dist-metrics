// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: subscribe.proto

package pl.project13.distmetrics.common.proto;

public final class Subscribe {
  private Subscribe() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SubscribeRequestOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string resourceId = 1;
    boolean hasResourceId();
    String getResourceId();
    
    // required .distmetrics.MetricType metricType = 2;
    boolean hasMetricType();
    pl.project13.distmetrics.common.proto.Common.MetricType getMetricType();
  }
  public static final class SubscribeRequest extends
      com.google.protobuf.GeneratedMessage
      implements SubscribeRequestOrBuilder {
    // Use SubscribeRequest.newBuilder() to construct.
    private SubscribeRequest(Builder builder) {
      super(builder);
    }
    private SubscribeRequest(boolean noInit) {}
    
    private static final SubscribeRequest defaultInstance;
    public static SubscribeRequest getDefaultInstance() {
      return defaultInstance;
    }
    
    public SubscribeRequest getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscribeRequest_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscribeRequest_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required string resourceId = 1;
    public static final int RESOURCEID_FIELD_NUMBER = 1;
    private java.lang.Object resourceId_;
    public boolean hasResourceId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getResourceId() {
      java.lang.Object ref = resourceId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          resourceId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getResourceIdBytes() {
      java.lang.Object ref = resourceId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        resourceId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required .distmetrics.MetricType metricType = 2;
    public static final int METRICTYPE_FIELD_NUMBER = 2;
    private pl.project13.distmetrics.common.proto.Common.MetricType metricType_;
    public boolean hasMetricType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public pl.project13.distmetrics.common.proto.Common.MetricType getMetricType() {
      return metricType_;
    }
    
    private void initFields() {
      resourceId_ = "";
      metricType_ = pl.project13.distmetrics.common.proto.Common.MetricType.Cpu;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasResourceId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMetricType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getResourceIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, metricType_.getNumber());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getResourceIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, metricType_.getNumber());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscribeRequest_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscribeRequest_fieldAccessorTable;
      }
      
      // Construct using pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        resourceId_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        metricType_ = pl.project13.distmetrics.common.proto.Common.MetricType.Cpu;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest.getDescriptor();
      }
      
      public pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest getDefaultInstanceForType() {
        return pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest.getDefaultInstance();
      }
      
      public pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest build() {
        pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest buildPartial() {
        pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest result = new pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.resourceId_ = resourceId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.metricType_ = metricType_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest) {
          return mergeFrom((pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest other) {
        if (other == pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest.getDefaultInstance()) return this;
        if (other.hasResourceId()) {
          setResourceId(other.getResourceId());
        }
        if (other.hasMetricType()) {
          setMetricType(other.getMetricType());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasResourceId()) {
          
          return false;
        }
        if (!hasMetricType()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              resourceId_ = input.readBytes();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              pl.project13.distmetrics.common.proto.Common.MetricType value = pl.project13.distmetrics.common.proto.Common.MetricType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                metricType_ = value;
              }
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string resourceId = 1;
      private java.lang.Object resourceId_ = "";
      public boolean hasResourceId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getResourceId() {
        java.lang.Object ref = resourceId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          resourceId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setResourceId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        resourceId_ = value;
        onChanged();
        return this;
      }
      public Builder clearResourceId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        resourceId_ = getDefaultInstance().getResourceId();
        onChanged();
        return this;
      }
      void setResourceId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        resourceId_ = value;
        onChanged();
      }
      
      // required .distmetrics.MetricType metricType = 2;
      private pl.project13.distmetrics.common.proto.Common.MetricType metricType_ = pl.project13.distmetrics.common.proto.Common.MetricType.Cpu;
      public boolean hasMetricType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public pl.project13.distmetrics.common.proto.Common.MetricType getMetricType() {
        return metricType_;
      }
      public Builder setMetricType(pl.project13.distmetrics.common.proto.Common.MetricType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        metricType_ = value;
        onChanged();
        return this;
      }
      public Builder clearMetricType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        metricType_ = pl.project13.distmetrics.common.proto.Common.MetricType.Cpu;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:distmetrics.SubscribeRequest)
    }
    
    static {
      defaultInstance = new SubscribeRequest(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:distmetrics.SubscribeRequest)
  }
  
  public interface SubscriptionResponseOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required int64 subscriptionId = 1;
    boolean hasSubscriptionId();
    long getSubscriptionId();
    
    // required string host = 2;
    boolean hasHost();
    String getHost();
    
    // required int32 port = 3;
    boolean hasPort();
    int getPort();
  }
  public static final class SubscriptionResponse extends
      com.google.protobuf.GeneratedMessage
      implements SubscriptionResponseOrBuilder {
    // Use SubscriptionResponse.newBuilder() to construct.
    private SubscriptionResponse(Builder builder) {
      super(builder);
    }
    private SubscriptionResponse(boolean noInit) {}
    
    private static final SubscriptionResponse defaultInstance;
    public static SubscriptionResponse getDefaultInstance() {
      return defaultInstance;
    }
    
    public SubscriptionResponse getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscriptionResponse_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscriptionResponse_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required int64 subscriptionId = 1;
    public static final int SUBSCRIPTIONID_FIELD_NUMBER = 1;
    private long subscriptionId_;
    public boolean hasSubscriptionId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public long getSubscriptionId() {
      return subscriptionId_;
    }
    
    // required string host = 2;
    public static final int HOST_FIELD_NUMBER = 2;
    private java.lang.Object host_;
    public boolean hasHost() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public String getHost() {
      java.lang.Object ref = host_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          host_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getHostBytes() {
      java.lang.Object ref = host_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        host_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required int32 port = 3;
    public static final int PORT_FIELD_NUMBER = 3;
    private int port_;
    public boolean hasPort() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getPort() {
      return port_;
    }
    
    private void initFields() {
      subscriptionId_ = 0L;
      host_ = "";
      port_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasSubscriptionId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasHost()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPort()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, subscriptionId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getHostBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, port_);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, subscriptionId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getHostBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, port_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscriptionResponse_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return pl.project13.distmetrics.common.proto.Subscribe.internal_static_distmetrics_SubscriptionResponse_fieldAccessorTable;
      }
      
      // Construct using pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        subscriptionId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        host_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        port_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse.getDescriptor();
      }
      
      public pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse getDefaultInstanceForType() {
        return pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse.getDefaultInstance();
      }
      
      public pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse build() {
        pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse buildPartial() {
        pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse result = new pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.subscriptionId_ = subscriptionId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.host_ = host_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.port_ = port_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse) {
          return mergeFrom((pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse other) {
        if (other == pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse.getDefaultInstance()) return this;
        if (other.hasSubscriptionId()) {
          setSubscriptionId(other.getSubscriptionId());
        }
        if (other.hasHost()) {
          setHost(other.getHost());
        }
        if (other.hasPort()) {
          setPort(other.getPort());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasSubscriptionId()) {
          
          return false;
        }
        if (!hasHost()) {
          
          return false;
        }
        if (!hasPort()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              subscriptionId_ = input.readInt64();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              host_ = input.readBytes();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              port_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required int64 subscriptionId = 1;
      private long subscriptionId_ ;
      public boolean hasSubscriptionId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public long getSubscriptionId() {
        return subscriptionId_;
      }
      public Builder setSubscriptionId(long value) {
        bitField0_ |= 0x00000001;
        subscriptionId_ = value;
        onChanged();
        return this;
      }
      public Builder clearSubscriptionId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        subscriptionId_ = 0L;
        onChanged();
        return this;
      }
      
      // required string host = 2;
      private java.lang.Object host_ = "";
      public boolean hasHost() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public String getHost() {
        java.lang.Object ref = host_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          host_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setHost(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        host_ = value;
        onChanged();
        return this;
      }
      public Builder clearHost() {
        bitField0_ = (bitField0_ & ~0x00000002);
        host_ = getDefaultInstance().getHost();
        onChanged();
        return this;
      }
      void setHost(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000002;
        host_ = value;
        onChanged();
      }
      
      // required int32 port = 3;
      private int port_ ;
      public boolean hasPort() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getPort() {
        return port_;
      }
      public Builder setPort(int value) {
        bitField0_ |= 0x00000004;
        port_ = value;
        onChanged();
        return this;
      }
      public Builder clearPort() {
        bitField0_ = (bitField0_ & ~0x00000004);
        port_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:distmetrics.SubscriptionResponse)
    }
    
    static {
      defaultInstance = new SubscriptionResponse(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:distmetrics.SubscriptionResponse)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_distmetrics_SubscribeRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_distmetrics_SubscribeRequest_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_distmetrics_SubscriptionResponse_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_distmetrics_SubscriptionResponse_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017subscribe.proto\022\013distmetrics\032\014common.p" +
      "roto\"S\n\020SubscribeRequest\022\022\n\nresourceId\030\001" +
      " \002(\t\022+\n\nmetricType\030\002 \002(\0162\027.distmetrics.M" +
      "etricType\"J\n\024SubscriptionResponse\022\026\n\016sub" +
      "scriptionId\030\001 \002(\003\022\014\n\004host\030\002 \002(\t\022\014\n\004port\030" +
      "\003 \002(\005B\'\n%pl.project13.distmetrics.common" +
      ".proto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_distmetrics_SubscribeRequest_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_distmetrics_SubscribeRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_distmetrics_SubscribeRequest_descriptor,
              new java.lang.String[] { "ResourceId", "MetricType", },
              pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest.class,
              pl.project13.distmetrics.common.proto.Subscribe.SubscribeRequest.Builder.class);
          internal_static_distmetrics_SubscriptionResponse_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_distmetrics_SubscriptionResponse_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_distmetrics_SubscriptionResponse_descriptor,
              new java.lang.String[] { "SubscriptionId", "Host", "Port", },
              pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse.class,
              pl.project13.distmetrics.common.proto.Subscribe.SubscriptionResponse.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          pl.project13.distmetrics.common.proto.Common.getDescriptor(),
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
