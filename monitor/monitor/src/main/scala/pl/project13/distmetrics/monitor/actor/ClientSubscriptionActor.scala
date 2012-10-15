package pl.project13.distmetrics.monitor.actor

import akka.actor.Actor
import pl.project13.distmetrics.common.proto
import proto.Common.MetricType
import proto.Measure.Measurement
import proto.Subscribe.SubscribeRequest
import proto.ProtoConversions
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.monitor.runner.MonitorMain
import java.nio.channels.{ServerSocketChannel, SelectionKey}
import pl.project13.distmetrics.monitor.config.MonitorConfig
import scalaz.Scalaz._
import java.util.concurrent.ConcurrentHashMap
import collection.JavaConverters._


class ClientSubscriptionActor(monitor: MonitorMain, config: MonitorConfig) extends Actor with ProtoConversions with Logging {

  type ResourceId = String
  type InterestPoint = (ResourceId, MetricType)

  type SubscriptionId = Int
  private val interestPointToSubscriptionId = new ConcurrentHashMap[InterestPoint, Set[SubscriptionId]]().asScala.withDefaultValue(Set())
  private val subscriptionIdToSelKey = new ConcurrentHashMap[SubscriptionId, SelectionKey]().asScala

  def receive = {
    case RegisterSensorChannel =>
      openSocketForSensor()


    case request: SubscribeRequest =>
      val resourceId = request.getResourceId
      val metricType = request.getMetricType

        val ChannelInformation(subscriptionId, selKey) = openSubscriptionSocketForClient()
        logger.info("Created channel for subscription [%s], for metrics [%s]".format(subscriptionId, key(resourceId, metricType)))


        interestPointToSubscriptionId((resourceId, metricType)) += subscriptionId
        subscriptionIdToSelKey put (subscriptionId, selKey)
        logger.info("subscriptionIdToSelKey = " + subscriptionIdToSelKey)

        sender ! SubscriptionCreated(subscriptionId)


    case SubscriptionDetailsFor(subscriptionId) =>
      subscriptionIdToSelKey.get(subscriptionId) map { selKey =>
        val localPort = selKey.channel().asInstanceOf[ServerSocketChannel].socket().getLocalPort
        logger.info("Routing subscriptionId [%s] to port [%s]".format(subscriptionId, localPort))

        sender ! SubscriptionResponse(subscriptionId, config.host, localPort)
      }


    case SubscriptionChannelsToPublish(measurement) =>
      logger.info("Identify selectionKey for measurement [%s], and request PushMeasurement".format(key(measurement)))
      val selectionKeys = findSelectionKeysFor(measurement)
      logger.info("Found [%s] clients listening for this measurement [%s]".format(selectionKeys.size, key(measurement)))

      selectionKeys foreach { sender ! PushMeasurement(measurement, _) }


    case SubscriptionDelete(subscriptionId) =>
      logger.info("Got delete [%s] subscription request, processing...".format(subscriptionId))
      deleteSubscription(subscriptionId)

  }


  def deleteSubscription(subscriptionId: Int) {
    subscriptionIdToSelKey.remove(subscriptionId) map { removedSelKey =>
      removedSelKey.channel().close()
      removedSelKey.cancel()
    }
  }

  def openSubscriptionSocketForClient() = {
    monitor.openNewChannel(writeable = true)
  }

  def openSocketForSensor() = {
    monitor.openNewChannel(writeable = false)
  }

  def key(measurement: Measurement): String = key(measurement.getResourceId, measurement.getMetricType)
  def key(resourceId: String, metricType: MetricType): String = resourceId + "-" + metricType.getValueDescriptor.getName

  def findSelectionKeysFor(measurement: Measurement): Set[SelectionKey] = {
    val subscriptionId = findSubscriptionIdsIdFor(measurement)
    subscriptionId.map(findSelectionKeyFor).flatten
  }

  def findSelectionKeyFor(subscriptionId: SubscriptionId): Option[SelectionKey] = {
    subscriptionIdToSelKey.get(subscriptionId)
  }

  def findSubscriptionIdsIdFor(measurement: Measurement): Set[SubscriptionId] =
    findSubscribersOf(measurement.getResourceId, measurement.getMetricType)

  def findSubscribersOf(theResourceId: String, theMetricType: MetricType): Set[SubscriptionId] = {
    logger.info("theResourceId = " + theResourceId)
    logger.info("theMetricType = " + theMetricType)
    logger.info("interestPointToSubscriptionId = " + interestPointToSubscriptionId)

    val found: Set[SubscriptionId] = interestPointToSubscriptionId find { case ((resourceId, metricType), subscriptionId) =>
      (theResourceId == resourceId) && (theMetricType equals metricType) } match {
      case Some(((resourceId, metricType), subscriptionIds)) => subscriptionIds
      case None => Set()
    }

    logger.info("found = " + found)

    found
  }
}
