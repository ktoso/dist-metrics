package pl.project13.distmetrics.monitor

import java.nio.channels.{SelectionKey, SocketChannel}
import runner.MonitorMain
import pl.project13.distmetrics.common.proto.Common.MetricType
import pl.project13.distmetrics.common.proto.Measure.Measurement

package object actor {

  case class DataReceived(channel: SocketChannel, data: Array[Byte])

  case class PushMeasurement(measurement: Measurement)

  case class SubscriptionDetailsFor(subscriptionId: Int)

  case class SubscriptionDelete(subscriptionId: Int)

  // channels
  case class ChannelInformation(nth: Int, selectionKey: SelectionKey)

}
