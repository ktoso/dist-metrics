package pl.project13.distmetrics.monitor.spray

import cc.spray.Directives
import cc.spray.http.StatusCode._
import cc.spray.http.{StatusCode, HttpHeaders}
import pl.project13.distmetrics.monitor.config.MonitorConfig

trait SubscriptionsService extends Directives {

  def config: MonitorConfig

  val Subscriptions = "subscriptions"

  val subscriptionsService = {
    path(Subscriptions) {
      post { context =>
        context.complete("got POST!")
//        context.complete(201, HttpHeaders.Location(config.path(Subscriptions, 1.toString))) // act like you created a resource
//        context.complete(201, HttpHeaders.Location(config.path(Subscriptions, 1.toString))) // act like you created a resource
      } ~
      get { context =>
        context.complete("got GET!")
      } ~
      delete { context =>
        context.complete("got DELETE!")
      }
    }
  }
}
