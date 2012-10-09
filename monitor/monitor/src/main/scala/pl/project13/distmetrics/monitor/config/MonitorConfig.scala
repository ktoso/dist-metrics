package pl.project13.distmetrics.monitor.config

import com.typesafe.config._

class MonitorConfig {
  private val conf = ConfigFactory.load("monitor.conf")

  val port = conf.getInt("port")
}

object MonitorConfig extends MonitorConfig