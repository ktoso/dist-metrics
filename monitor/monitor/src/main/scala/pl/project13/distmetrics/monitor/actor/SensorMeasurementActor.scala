package pl.project13.distmetrics.monitor.actor

import akka.actor.{ActorRef, Actor}
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.common.proto.ProtoConversions
import java.nio.channels.ServerSocketChannel

class SensorMeasurementActor(subscriptionActor: ActorRef) extends Actor with ProtoConversions with Logging {

  var selectionRouterActor: Option[ActorRef] = None

  protected def receive = {

    case RegisterSelectionRouterActor(selectionActor) =>
      this.selectionRouterActor = Some(selectionActor)

    case DataReceived(channel, data) =>
      logger.debug("Recieved [%s] bytes of data, parsing as [%s]".format(data.size, "Measurement"))

      val measurement = Measurement.parseFrom(data)

      logger.debug(measurement.toString.split("\n").mkString("[", ", ", "]"))

      subscriptionActor ! SubscriptionChannelsToPublish(measurement)

    case PushMeasurement(measurement, selectionKey) =>
      val bytes = measurement.toByteArray
      val port = selectionKey.channel.asInstanceOf[ServerSocketChannel].socket.getLocalPort
      logger.info("Enqueue write of Measurement to channel ([%s] bytes)...".format(bytes.size))
      selectionRouterActor map { _ ! EnqueueWrite(bytes, port) }
  }

}
