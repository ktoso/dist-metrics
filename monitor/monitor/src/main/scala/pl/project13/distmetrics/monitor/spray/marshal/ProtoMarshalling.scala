package pl.project13.distmetrics.monitor.spray.marshal

import pl.project13.distmetrics.common.proto.{ProtoConversions, Subscribe}
import cc.spray.typeconversion._
import cc.spray.http.HttpContent

trait ProtoMarshalling extends ProtoConversions {
  implicit val subscribeRequestDeserializer11 = new Unmarshaller[Subscribe.SubscribeRequest] {
    def apply(v1: Option[HttpContent]) = try {
      Right(Subscribe.SubscribeRequest.parseFrom(v1.get.buffer))
    } catch {
      case _: Exception => Left(MalformedContent("Unable to convert [%s] to [Subscribe.SubscribeRequest"))
    }
  }

  implicit val subscribeResponseDeserializer = new Deserializer[String, Subscribe.SubscriptionResponse] {
    override def apply(in: String): Either[MalformedContent, Subscribe.SubscriptionResponse] = try {
      Right(in.getBytes)
    } catch {
      case _: Exception => Left(MalformedContent("Unable to convert [%s] to [Subscribe.SubscriptionResponse"))
    }
  }
}
