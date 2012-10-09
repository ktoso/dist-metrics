package pl.project13.distmetrics.monitor.spray

import cc.spray.Directives
import pl.project13.distmetrics.monitor.config.MonitorConfig

/**
 * {{{
 * ktoso@moon ~/code/dist-metrics/monitor [master*]
 * $ http DELETE localhost:8080
 *
 * HTTP/1.1 200 OK
 * Content-Length: 82
 * Content-Type: text/plain
 * Date: Tue, 09 Oct 2012 22:52:56 GMT
 * Server: spray-can/1.0-M1
 *
 * [POST /subscriptions] to subscribe, or [DELETE /subscriptions/####] to unsubscribe
 * }}}
 */
trait RootService extends Directives {

  def config: MonitorConfig

  val rootService = {
    path("") {
      _.complete("[POST /subscriptions] to subscribe, or [DELETE /subscriptions/####] to unsubscribe")
    }
  }
}
