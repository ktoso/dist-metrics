// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: measure.proto

package pl.project13.distmetrics.common.proto;

public final class Measure {
  private Measure() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface MeasurementOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string resourceId = 1;
    boolean hasResourceId();
    String getResourceId();
    
    // required .distmetrics.MetricType metricType = 2;
    boolean hasMetricType();
    pl.project13.distmetrics.common.proto.Common.MetricType getMetricType();
    
    // required string value = 3;
    boolean hasValue();
    String getValue();
    
    // required int64 timestamp = 4;
    boolean hasTimestamp();
    long getTimestamp();
  }
  public static final class Measurement extends
      com.google.protobuf.GeneratedMessage
      implements MeasurementOrBuilder {
    // Use Measurement.newBuilder() to construct.
    private Measurement(Builder builder) {
      super(builder);
    }
    private Measurement(boolean noInit) {}
    
    private static final Measurement defaultInstance;
    public static Measurement getDefaultInstance() {
      return defaultInstance;
    }
    
    public Measurement getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pl.project13.distmetrics.common.proto.Measure.internal_static_distmetrics_Measurement_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pl.project13.distmetrics.common.proto.Measure.internal_static_distmetrics_Measurement_fieldAccessorTable;
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
    
    // required string value = 3;
    public static final int VALUE_FIELD_NUMBER = 3;
    private java.lang.Object value_;
    public boolean hasValue() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getValue() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          value_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getValueBytes() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        value_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required int64 timestamp = 4;
    public static final int TIMESTAMP_FIELD_NUMBER = 4;
    private long timestamp_;
    public boolean hasTimestamp() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public long getTimestamp() {
      return timestamp_;
    }
    
    private void initFields() {
      resourceId_ = "";
      metricType_ = pl.project13.distmetrics.common.proto.Common.MetricType.Cpu;
      value_ = "";
      timestamp_ = 0L;
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
      if (!hasValue()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTimestamp()) {
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
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getValueBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt64(4, timestamp_);
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
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getValueBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, timestamp_);
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
    
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseDelimitedFrom(
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
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.project13.distmetrics.common.proto.Measure.Measurement parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(pl.project13.distmetrics.common.proto.Measure.Measurement prototype) {
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
       implements pl.project13.distmetrics.common.proto.Measure.MeasurementOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return pl.project13.distmetrics.common.proto.Measure.internal_static_distmetrics_Measurement_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return pl.project13.distmetrics.common.proto.Measure.internal_static_distmetrics_Measurement_fieldAccessorTable;
      }
      
      // Construct using pl.project13.distmetrics.common.proto.Measure.Measurement.newBuilder()
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
        value_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        timestamp_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return pl.project13.distmetrics.common.proto.Measure.Measurement.getDescriptor();
      }
      
      public pl.project13.distmetrics.common.proto.Measure.Measurement getDefaultInstanceForType() {
        return pl.project13.distmetrics.common.proto.Measure.Measurement.getDefaultInstance();
      }
      
      public pl.project13.distmetrics.common.proto.Measure.Measurement build() {
        pl.project13.distmetrics.common.proto.Measure.Measurement result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private pl.project13.distmetrics.common.proto.Measure.Measurement buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        pl.project13.distmetrics.common.proto.Measure.Measurement result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public pl.project13.distmetrics.common.proto.Measure.Measurement buildPartial() {
        pl.project13.distmetrics.common.proto.Measure.Measurement result = new pl.project13.distmetrics.common.proto.Measure.Measurement(this);
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
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.value_ = value_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.timestamp_ = timestamp_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof pl.project13.distmetrics.common.proto.Measure.Measurement) {
          return mergeFrom((pl.project13.distmetrics.common.proto.Measure.Measurement)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(pl.project13.distmetrics.common.proto.Measure.Measurement other) {
        if (other == pl.project13.distmetrics.common.proto.Measure.Measurement.getDefaultInstance()) return this;
        if (other.hasResourceId()) {
          setResourceId(other.getResourceId());
        }
        if (other.hasMetricType()) {
          setMetricType(other.getMetricType());
        }
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        if (other.hasTimestamp()) {
          setTimestamp(other.getTimestamp());
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
        if (!hasValue()) {
          
          return false;
        }
        if (!hasTimestamp()) {
          
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
            case 26: {
              bitField0_ |= 0x00000004;
              value_ = input.readBytes();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              timestamp_ = input.readInt64();
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
      
      // required string value = 3;
      private java.lang.Object value_ = "";
      public boolean hasValue() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getValue() {
        java.lang.Object ref = value_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          value_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setValue(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        value_ = value;
        onChanged();
        return this;
      }
      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000004);
        value_ = getDefaultInstance().getValue();
        onChanged();
        return this;
      }
      void setValue(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        value_ = value;
        onChanged();
      }
      
      // required int64 timestamp = 4;
      private long timestamp_ ;
      public boolean hasTimestamp() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public long getTimestamp() {
        return timestamp_;
      }
      public Builder setTimestamp(long value) {
        bitField0_ |= 0x00000008;
        timestamp_ = value;
        onChanged();
        return this;
      }
      public Builder clearTimestamp() {
        bitField0_ = (bitField0_ & ~0x00000008);
        timestamp_ = 0L;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:distmetrics.Measurement)
    }
    
    static {
      defaultInstance = new Measurement(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:distmetrics.Measurement)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_distmetrics_Measurement_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_distmetrics_Measurement_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmeasure.proto\022\013distmetrics\032\014common.pro" +
      "to\"p\n\013Measurement\022\022\n\nresourceId\030\001 \002(\t\022+\n" +
      "\nmetricType\030\002 \002(\0162\027.distmetrics.MetricTy" +
      "pe\022\r\n\005value\030\003 \002(\t\022\021\n\ttimestamp\030\004 \002(\003B\'\n%" +
      "pl.project13.distmetrics.common.proto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_distmetrics_Measurement_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_distmetrics_Measurement_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_distmetrics_Measurement_descriptor,
              new java.lang.String[] { "ResourceId", "MetricType", "Value", "Timestamp", },
              pl.project13.distmetrics.common.proto.Measure.Measurement.class,
              pl.project13.distmetrics.common.proto.Measure.Measurement.Builder.class);
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
