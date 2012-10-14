package pl.project13.distmetrics.monitor.actor

import akka.actor.{ActorRef, Actor}
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.common.proto.ProtoConversions
import pl.project13.distmetrics.monitor.channel.ChannelWriteOperation

class SensorMeasurementActor(subscriptionActor: ActorRef) extends Actor with ProtoConversions with Logging
  with ChannelWriteOperation {

  protected def receive = {
    case DataReceived(channel, data) =>
      logger.debug("Recieved [%s] bytes of data, parsing as [%s]".format(data.size, "Measurement"))

      val measurement = Measurement.parseFrom(data)

      logger.debug(measurement.toString.split("\n").mkString("[", ", ", "]"))

      subscriptionActor ! SubscriptionChannelsToPublish(measurement)

    case PushMeasurement(measurement, selectionKey) =>
      val bytes = measurement.toByteArray
      logger.info("Writing Measurement to channel ([%s] bytes)...".format(bytes.size))
      write(bytes, selectionKey)
  }

}
