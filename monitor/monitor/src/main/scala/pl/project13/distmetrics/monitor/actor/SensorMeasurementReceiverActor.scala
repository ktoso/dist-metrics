package pl.project13.distmetrics.monitor.actor

import akka.actor.Actor
import pl.project13.distmetrics.common.proto
import java.nio.channels.SocketChannel
import proto.{Measurement, SubscribeRequest, ProtoConversions}
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.monitor.channel.{ChannelReadOperation, ChannelOperations}

class SensorMeasurementReceiverActor extends Actor with ProtoConversions with Logging {

  protected def receive = {
    case DataReceived(channel, data) =>
      logger.debug("Recieved [%s] bytes of data, parsing as [%s]".format(data.size, "Measurement"))

      val metric = Measurement.parseFrom(data)

      logger.debug(metric.toString.split("\n").mkString("[", ", ", "]"))
  }

}
