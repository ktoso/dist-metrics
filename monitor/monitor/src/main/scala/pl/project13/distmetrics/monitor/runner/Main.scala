package pl.project13.distmetrics.monitor.runner

import java.nio._
import channels._
import pl.project13.distmetrics.monitor.config.MonitorConfig
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.monitor.util.JavaNioConversions
import collection.JavaConversions._
import annotation.tailrec
import java.util.concurrent.atomic.AtomicInteger
import pl.project13.distmetrics.monitor.channel.ChannelOperations

trait MonitorMain extends Logging
  with ChannelOperations {
  def config: MonitorConfig

  val selector = Selector.open()

  val openChannels = new AtomicInteger(0)

  @tailrec final def loop() {
    selector.select()

    val selKeysIterator = selector.selectedKeys.iterator
    selKeysIterator foreach { process(selector, _) }

    loop()
  }

  def process(selector: Selector, key: SelectionKey) = try {
    if (key.isValid && key.isConnectable) {
      val channel = key.socketChannel

      channel.finishConnect() match {
        case true =>
        case _ => key.cancel()
      }
    }
  } catch {
    case ex: Exception =>
      key.cancel()
      logger.error("Got IO exception while processing. Channel cancelled.", ex)
  }

  def openNewChannel() {
    val portToUse = config.port + openChannels.incrementAndGet()

    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.configureBlocking(false)
    serverSocketChannel.socket().bind(portToUse)

    logger.info("Opened new channel on [%s]. Already [%s] channels open".format(portToUse, openChannels.get))
  }
}

object Main extends App with MonitorMain {

  val config = MonitorConfig

  loop()
}
