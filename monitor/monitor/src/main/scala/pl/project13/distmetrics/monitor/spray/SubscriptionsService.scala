package pl.project13.distmetrics.monitor.spray

import cc.spray.{RequestContext, Directives}
import cc.spray.http.StatusCode._
import cc.spray.http.HttpHeaders._
import marshal.ProtoMarshalling
import pl.project13.distmetrics.monitor.config.MonitorConfig
import com.weiglewilczek.slf4s.Logging
import cc.spray.directives.{IntNumber, LongNumber}
import pl.project13.distmetrics.common.proto.Subscribe
import akka.actor.ActorRef
import cc.spray.can.model.HttpResponse
import akka.dispatch.Await
import akka.util.duration._
import akka.util.Timeout
import cc.spray.http.{HttpHeaders, StatusCodes, StatusCode}
import akka.pattern._
import pl.project13.distmetrics.monitor.actor.{SubscriptionDetailsFor, SubscriptionDelete}

trait SubscriptionsService extends Directives with Logging
  with ProtoMarshalling {

  def config: MonitorConfig

  def subscriptionHandler: ActorRef

  implicit val atMost = 30.seconds
  implicit val timeout = Timeout(atMost)

  val Subscriptions = "subscriptions"

  val subscriptionsService = {
    pathPrefix(Subscriptions) {
      post {
        content(as[Subscribe.SubscribeRequest]) { request =>
          detach {
            handleRegistration(request)
          }
        }
      } ~
      path(IntNumber) { subscriptionId =>
        get {
          detach {
            handleObtainPort(subscriptionId)
          }
        } ~
        delete { context =>
          handleDeleteSubscription(subscriptionId)
        }
      }
    }
  }


  def handleDeleteSubscription(subscriptionId: Int) {
    subscriptionHandler ! SubscriptionDelete(subscriptionId)
  }

  def handleObtainPort(subscriptionId: Int): (RequestContext) => Unit = {
    val futureResponse = subscriptionHandler ? SubscriptionDetailsFor(subscriptionId)
    val response = Await.result(futureResponse, atMost).asInstanceOf[Subscribe.SubscriptionResponse]

    _.complete(StatusCodes.OK, response.toByteArray)
  }

  def handleRegistration(request: Subscribe.SubscribeRequest): (RequestContext) => Unit = {
    val futurePort = subscriptionHandler ? request
    val port = Await.result(futurePort, atMost).asInstanceOf[Int]

    _.complete(StatusCodes.Created, HttpHeaders.Location("localhost:8080/" + Subscriptions + "/" + port) :: Nil, "")
  }

}
