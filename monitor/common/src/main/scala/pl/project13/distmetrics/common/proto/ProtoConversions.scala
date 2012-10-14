package pl.project13.distmetrics.common.proto

import java.util.Date
import scalaz.Scalaz._

trait ProtoConversions {

  implicit def asMeasurement(s: Array[Byte]) = Measure.Measurement.parseFrom(s)

  implicit def asSubscribeRequest(s: Array[Byte]) = Subscribe.SubscribeRequest.parseFrom(s)

  implicit def asSubscriptionResponse(s: Array[Byte]) = Subscribe.SubscriptionResponse.parseFrom(s)

  object Measurement extends ProtoConversions {

    def parseFrom(bytes: Array[Byte]) = Measure.Measurement.parseFrom(bytes)

    def apply(resourceId: String, value: String, metricType: Common.MetricType, timestamp: Date = new Date) =
      Measure.Measurement.newBuilder
        .setResourceId(resourceId)
        .setTimestamp(timestamp.getTime)
        .setValue(value)
        .setMetricType(metricType)
        .build()

    def unapply(bytes: Array[Byte]): Option[Measure.Measurement] = try {
      asMeasurement(bytes).some
    } catch {
      case ex: Exception => None
    }
  }

  object SubscribeRequest extends ProtoConversions {
    def parseFrom(bytes: Array[Byte]) = Subscribe.SubscribeRequest.parseFrom(bytes)

    def apply(resourceId: String, metricType: Common.MetricType) =
      Subscribe.SubscribeRequest.newBuilder
        .setResourceId(resourceId)
        .setMetricType(metricType)
        .build()

    def unapply(bytes: Array[Byte]): Option[Subscribe.SubscribeRequest] = try {
      asSubscribeRequest(bytes).some
    } catch {
      case ex: Exception => None
    }
  }

  object SubscriptionResponse extends ProtoConversions {
    def apply(bytes: Array[Byte]) = Subscribe.SubscriptionResponse.parseFrom(bytes)

    def apply(id: Int, host: String, port: Int) =
      Subscribe.SubscriptionResponse.newBuilder
        .setSubscriptionId(id)
        .setHost(host)
        .setPort(port)
        .build()

    def unapply(bytes: Array[Byte]): Option[Subscribe.SubscriptionResponse] = try {
      asSubscriptionResponse(bytes).some
    } catch {
      case ex: Exception => None
    }
  }

}