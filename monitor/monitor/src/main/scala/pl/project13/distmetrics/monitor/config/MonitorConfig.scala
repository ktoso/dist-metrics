package pl.project13.distmetrics.monitor.config

import com.typesafe.config._

class MonitorConfig {
  private val conf = ConfigFactory.load("monitor.conf")

  val host = conf.getString("host")
  val port = conf.getInt("port")
  def path(parts: String*) = host + ":" + port + "/" + parts.mkString("/")

  val tcpPort = conf.getInt("tcp_port")

}

object MonitorConfig extends MonitorConfig