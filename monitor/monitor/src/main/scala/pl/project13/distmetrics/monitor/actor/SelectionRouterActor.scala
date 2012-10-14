package pl.project13.distmetrics.monitor.actor

import akka.actor.{ActorRef, Actor}
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.monitor.channel.{ChannelReadOperation, ChannelAcceptOperation}
import java.nio.channels.{SocketChannel, Selector, SelectionKey}

class SelectionRouterActor(metricHandlerActor: ActorRef, subscriptionHandlerActor: ActorRef) extends Actor with Logging
  with ChannelAcceptOperation
  with ChannelReadOperation {

  var selector: Option[Selector] = None

  protected def receive = {
    case s: Selector =>
      logger.info("Registered selector with [%s] ".format(getClass.getSimpleName))
      this.selector = Some(s)

    case selectionKey: SelectionKey if selectionKey.isValid && selectionKey.isWritable =>
      logger.debug("Write to selectionKey...")
      selectionKey.cancel()

    case selectionKey: SelectionKey if selectionKey.isValid &&selectionKey.isReadable =>
      read(selectionKey)
      logger.debug("Read from selectionKey...")

    case selectionKey: SelectionKey if selectionKey.isValid && selectionKey.isAcceptable =>
      selector map { sel =>
        accept(sel, selectionKey)
        logger.debug("Accepted selectionKey...")
      }
      sender ! "OK"

    case _ =>
  }

  def handleReadData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long) {
    logger.info("Sending read data to metric actor...")
    metricHandlerActor ! DataReceived(socketChannel, data.take(bytes.toInt))
  }
}
