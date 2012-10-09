# Generated by the protocol buffer compiler.  DO NOT EDIT!

from google.protobuf import descriptor
from google.protobuf import message
from google.protobuf import reflection
from google.protobuf import descriptor_pb2
# @@protoc_insertion_point(imports)


import common_pb2

DESCRIPTOR = descriptor.FileDescriptor(
  name='subscribe.proto',
  package='distmetrics',
  serialized_pb='\n\x0fsubscribe.proto\x12\x0b\x64istmetrics\x1a\x0c\x63ommon.proto\"S\n\x10SubscribeRequest\x12\x12\n\nresourceId\x18\x01 \x02(\t\x12+\n\nmetricType\x18\x02 \x02(\x0e\x32\x17.distmetrics.MetricType\"J\n\x14SubscriptionResponse\x12\x16\n\x0esubscriptionId\x18\x01 \x02(\x03\x12\x0c\n\x04host\x18\x02 \x02(\t\x12\x0c\n\x04port\x18\x03 \x02(\x05\x42\'\n%pl.project13.distmetrics.common.proto')




_SUBSCRIBEREQUEST = descriptor.Descriptor(
  name='SubscribeRequest',
  full_name='distmetrics.SubscribeRequest',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='resourceId', full_name='distmetrics.SubscribeRequest.resourceId', index=0,
      number=1, type=9, cpp_type=9, label=2,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='metricType', full_name='distmetrics.SubscribeRequest.metricType', index=1,
      number=2, type=14, cpp_type=8, label=2,
      has_default_value=False, default_value=1,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=46,
  serialized_end=129,
)


_SUBSCRIPTIONRESPONSE = descriptor.Descriptor(
  name='SubscriptionResponse',
  full_name='distmetrics.SubscriptionResponse',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    descriptor.FieldDescriptor(
      name='subscriptionId', full_name='distmetrics.SubscriptionResponse.subscriptionId', index=0,
      number=1, type=3, cpp_type=2, label=2,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='host', full_name='distmetrics.SubscriptionResponse.host', index=1,
      number=2, type=9, cpp_type=9, label=2,
      has_default_value=False, default_value=unicode("", "utf-8"),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
    descriptor.FieldDescriptor(
      name='port', full_name='distmetrics.SubscriptionResponse.port', index=2,
      number=3, type=5, cpp_type=1, label=2,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      options=None),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  options=None,
  is_extendable=False,
  extension_ranges=[],
  serialized_start=131,
  serialized_end=205,
)

_SUBSCRIBEREQUEST.fields_by_name['metricType'].enum_type = common_pb2._METRICTYPE
DESCRIPTOR.message_types_by_name['SubscribeRequest'] = _SUBSCRIBEREQUEST
DESCRIPTOR.message_types_by_name['SubscriptionResponse'] = _SUBSCRIPTIONRESPONSE

class SubscribeRequest(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _SUBSCRIBEREQUEST
  
  # @@protoc_insertion_point(class_scope:distmetrics.SubscribeRequest)

class SubscriptionResponse(message.Message):
  __metaclass__ = reflection.GeneratedProtocolMessageType
  DESCRIPTOR = _SUBSCRIPTIONRESPONSE
  
  # @@protoc_insertion_point(class_scope:distmetrics.SubscriptionResponse)

# @@protoc_insertion_point(module_scope)
