package pl.project13.distmetrics.monitor.actor

import akka.actor.{ActorRef, Actor}
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
import collection.JavaConversions._
import scalaz.Scalaz._

class ClientSubscriptionActor(monitor: MonitorMain, config: MonitorConfig) extends Actor with ProtoConversions with Logging {

  type ResourceId = String
  type InterestedIn = (ResourceId, MetricType)

  type SubscriptionId = Int
  private val topicToSubscriptionId = new ConcurrentHashMap[InterestedIn, SubscriptionId]()
  private val subscriptionIdToSelKey = new ConcurrentHashMap[SubscriptionId, SelectionKey]()

  def receive = {
    case RegisterSensorChannel =>
      openSocketForSensor()


    case request: SubscribeRequest =>
      val resourceId = request.getResourceId
      val metricType = request.getMetricType

      findSubscribersOf(resourceId, metricType) match {
        case None => // create new channel
          val ChannelInformation(subscriptionId, selKey) = openSubscriptionSocketForClient()
          logger.info("Created channel for subscription [%s], for metrics [%s]".format(subscriptionId, key(resourceId, metricType)))

          topicToSubscriptionId((resourceId, metricType)) = subscriptionId
          subscriptionIdToSelKey(subscriptionId) = selKey

          sender ! SubscriptionCreatedOrFound(subscriptionId, isFreshResource = true)

        case Some((_, subscriptionId)) => // answer with cached subscription Id
          sender ! SubscriptionCreatedOrFound(subscriptionId, isFreshResource = false)
      }


    case SubscriptionDetailsFor(subscriptionId) =>
      val selKey = subscriptionIdToSelKey(subscriptionId)
      val localPort = selKey.channel().asInstanceOf[ServerSocketChannel].socket().getLocalPort
      logger.info("Routing subscriptionId [%s] to port [%s]".format(subscriptionId, localPort))

      sender ! SubscriptionResponse(subscriptionId, config.host, localPort)


    case SubscriptionChannelsToPublish(measurement) =>
      logger.info("Identify selectionKey for measurement [%s], and request PushMeasurement".format(key(measurement)))
      findSelectionKeyFor(measurement) match {
        case Some(selectionKey) =>
          sender ! PushMeasurement(measurement, selectionKey)

        case None =>
          logger.debug("No one is listening for [%s]".format(key(measurement)))
      }


    case SubscriptionDelete(subscriptionId) =>
      deleteSubscription(subscriptionId)

  }


  def deleteSubscription(subscriptionId: Int) {
//    subscriptionIdToSelKey.remove(subscriptionId)
//
//    val before = topicToSubscriptionId.size()
//    topicToSubscriptionId retain { (topic, id) => id /== subscriptionId }
//    val after = topicToSubscriptionId.size()
//    require(before > after, "Should have made it smaller. From [%s] to [%s] ".format(before, after))
  }

  def openSubscriptionSocketForClient() = {
    monitor.openNewChannel(writeable = true)
  }

  def openSocketForSensor() = {
    monitor.openNewChannel(writeable = false)
  }

  def key(measurement: Measurement): String = key(measurement.getResourceId, measurement.getMetricType)
  def key(resourceId: String, metricType: MetricType): String = resourceId + "-" + metricType.getValueDescriptor.getName

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
