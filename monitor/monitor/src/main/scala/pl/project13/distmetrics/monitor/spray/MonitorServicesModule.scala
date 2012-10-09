package pl.project13.distmetrics.monitor.spray

import com.weiglewilczek.slf4s.Logging
import cc.spray.Directives

trait MonitorServicesModule
  extends Directives
  with RootService
  with SubscriptionsService
