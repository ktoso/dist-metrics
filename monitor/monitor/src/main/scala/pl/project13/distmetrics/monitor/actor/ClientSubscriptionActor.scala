package pl.project13.distmetrics.monitor.actor

import akka.actor.Actor
import pl.project13.distmetrics.common.proto
import proto.Common.MetricType
import proto.Measure.Measurement
import proto.Subscribe.SubscribeRequest
import proto.{ProtoConversions}
import com.weiglewilczek.slf4s.Logging
import java.util.concurrent.ConcurrentHashMap
import collection.JavaConversions._
import pl.project13.distmetrics.monitor.runner.MonitorMain
import pl.project13.distmetrics.monitor.channel.ChannelWriteOperation
import java.nio.channels.{ServerSocketChannel, SocketChannel, SelectionKey}
import pl.project13.distmetrics.monitor.config.MonitorConfig

class ClientSubscriptionActor(monitor: MonitorMain, config: MonitorConfig) extends Actor with ProtoConversions with Logging
  with ChannelWriteOperation {

  type ResourceId = String
  type InterestedIn = (ResourceId, MetricType)

  type SubscriptionId = Int
  private val topicToSubscriptionId = new ConcurrentHashMap[InterestedIn, SubscriptionId]()
  private val subscriptionIdToSelKey = new ConcurrentHashMap[SubscriptionId, SelectionKey]()

  protected def receive = {
    case RegisterSensorChannel =>
      openSocketForSensor()

    case request: SubscribeRequest =>
      val resourceId = request.getResourceId
      val metricType = request.getMetricType

      findSubscribersOf(resourceId, metricType) match {
        case None => // create new channel
          val ChannelInformation(subscriptionId, selKey) = openSubscriptionSocketForClient()
          logger.info("Created channel for subscription [%s] on port [%s], for metrics [%s]".format(subscriptionId, selKey, resourceId + "-" + metricType))

          topicToSubscriptionId((resourceId, metricType)) = subscriptionId
          subscriptionIdToSelKey(subscriptionId) = selKey

          sender ! subscriptionId

        case Some((_, subscriptionId)) => // answer with cached subscription Id
          sender ! subscriptionId
      }

    case SubscriptionDetailsFor(subscriptionId) =>
      val selKey = subscriptionIdToSelKey(subscriptionId)
      val localPort = selKey.channel().asInstanceOf[ServerSocketChannel].socket().getLocalPort
      logger.info("Routing subscriptionId [%s] to port [%s]".format(subscriptionId, localPort))

      sender ! SubscriptionResponse(subscriptionId, config.host, localPort)

    case SubscriptionChannelsToPublish(measurement) =>
      logger.info("Identify selectionKey for measurement [%s-%s], and request PushMeasurement".format(measurement.getResourceId, measurement.getMetricType))
      findSelectionKeyFor(measurement) match {
        case Some(selectionKey) => sender ! PushMeasurement(measurement, selectionKey)
        case None => logger.debug("No one is listening for [%s-%s]".format(measurement.getResourceId, measurement.getMetricType))
      }


    case SubscriptionDelete(subscriptionId) =>
//      closeSubscription()
      topicToSubscriptionId.remove(subscriptionId)

  }

  def openSubscriptionSocketForClient() = {
    monitor.openNewChannel()
  }

  def openSocketForSensor() = {
    monitor.openNewChannel()
  }

  def key(resourceId: String, metricType: MetricType) = resourceId + "-" + metricType.getNumber

  def findSelectionKeyFor(measurement: Measurement): Option[SelectionKey] = {
    val subscriptionId = findSubscriptionIdIdFor(measurement)
    subscriptionId map { findSelectionKeyFor(_) }
  }

  def findSelectionKeyFor(subscriptionId: SubscriptionId): SelectionKey =
    subscriptionIdToSelKey(subscriptionId)

  def findSubscriptionIdIdFor(measurement: Measurement): Option[SubscriptionId] =
    findSubscribersOf(measurement.getResourceId, measurement.getMetricType).map(_._2)

  def findSubscribersOf(theResourceId: String, theMetricType: MetricType) =
    topicToSubscriptionId find { case ((resourceId, metricType), subscriptionId) =>
      (theResourceId == resourceId) && (theMetricType equals metricType)
    }
}
