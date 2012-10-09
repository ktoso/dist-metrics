package pl.project13.distmetrics.monitor.spray

import cc.spray.Directives

trait MonitorServicesModule extends Directives
  with RootService
  with SubscriptionsService
