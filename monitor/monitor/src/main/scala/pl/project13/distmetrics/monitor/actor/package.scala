package pl.project13.distmetrics.monitor

import java.nio.channels.{SelectionKey, SocketChannel}
import pl.project13.distmetrics.common.proto.Measure.Measurement
import pl.project13.distmetrics.common.proto.Measure
import akka.actor.ActorRef

package object actor {

  case object RegisterSensorChannel

  case class RegisterSelectionRouterActor(actor: ActorRef)

  case class EnqueueWrite(bytes: Array[Byte], port: Int)

  case class SubscriptionCreatedOrFound(subscriptionId: Int, isFreshResource: Boolean)

  case class DataReceived(channel: SocketChannel, data: Array[Byte])

  case class PushMeasurement(measurement: Measure.Measurement, selectionKey: SelectionKey)

  case class SubscriptionDetailsFor(subscriptionId: Int)

  case class SubscriptionChannelsToPublish(measurement: Measurement)

  case class SubscriptionDelete(subscriptionId: Int)

  // channels
  case class ChannelInformation(nth: Int, selectionKey: SelectionKey)

}
