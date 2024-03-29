// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: common.proto

package pl.project13.distmetrics.common.proto;

public final class Common {

  private Common() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public enum MetricType
      implements com.google.protobuf.ProtocolMessageEnum {
    Cpu(0, 1),
    MemFree(1, 2),
    MemUsed(2, 3),
    MemMax(3, 4),
    ;
    
    public static final int Cpu_VALUE = 1;
    public static final int MemFree_VALUE = 2;
    public static final int MemUsed_VALUE = 3;
    public static final int MemMax_VALUE = 4;
    
    
    public final int getNumber() { return value; }
    
    public static MetricType valueOf(int value) {
      switch (value) {
        case 1: return Cpu;
        case 2: return MemFree;
        case 3: return MemUsed;
        case 4: return MemMax;
        default: return null;
      }
    }
    
    public static com.google.protobuf.Internal.EnumLiteMap<MetricType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<MetricType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MetricType>() {
            public MetricType findValueByNumber(int number) {
              return MetricType.valueOf(number);
            }
          };
    
    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return pl.project13.distmetrics.common.proto.Common.getDescriptor().getEnumTypes().get(0);
    }
    
    private static final MetricType[] VALUES = {
      Cpu, MemFree, MemUsed, MemMax, 
    };
    
    public static MetricType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }
    
    private final int index;
    private final int value;
    
    private MetricType(int index, int value) {
      this.index = index;
      this.value = value;
    }
    
    // @@protoc_insertion_point(enum_scope:distmetrics.MetricType)
  }
  
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014common.proto\022\013distmetrics*;\n\nMetricTyp" +
      "e\022\007\n\003Cpu\020\001\022\013\n\007MemFree\020\002\022\013\n\007MemUsed\020\003\022\n\n\006" +
      "MemMax\020\004B\'\n%pl.project13.distmetrics.com" +
      "mon.proto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
