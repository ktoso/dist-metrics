package pl.project13.distmetrics.monitor

import java.nio.channels.SocketChannel
import runner.MonitorMain

package object actor {

  case class DataReceived(main: MonitorMain, channel: SocketChannel, data: Array[Byte])
}
