package pl.project13.distmetrics.monitor.actor

import akka.actor.{OneForOneStrategy, ActorRef, Actor}
import com.weiglewilczek.slf4s.Logging
import pl.project13.distmetrics.monitor.channel.{ChannelWriteOperation, ChannelReadOperation, ChannelAcceptOperation}
import java.nio.channels.{ServerSocketChannel, SocketChannel, Selector, SelectionKey}
import collection.mutable
import pl.project13.distmetrics.monitor.runner.MonitorMain
import akka.actor.SupervisorStrategy.Restart
import pl.project13.distmetrics.monitor.util.JavaNioConversions

class SelectionRouterActor(monitor: MonitorMain, metricHandlerActor: ActorRef, subscriptionHandlerActor: ActorRef) extends Actor with Logging
  with JavaNioConversions
  with ChannelAcceptOperation
  with ChannelWriteOperation
  with ChannelReadOperation {

  var selector: Option[Selector] = None

  val writeQueue = mutable.Queue[(Array[Byte], Int)]()

  override def preStart() {
    metricHandlerActor ! RegisterSelectionRouterActor(self)
  }

  def receive = {
    case s: Selector =>
      logger.info("Registered selector with [%s] ".format(getClass.getSimpleName))
      this.selector = Some(s)


    case EnqueueWrite(bytes, port) =>
      logger.info("Enqueued write of [%s] bytes to [%s]".format(bytes.size, port))
      writeQueue enqueue ((bytes, port))

    case selectionKey: SelectionKey if selectionKey.isValid && selectionKey.isAcceptable =>
      selector map { sel =>
        if (selectionKey.serverSocketChannelLocalPort == monitor.config.sensorPort)
          acceptForRead(sel, selectionKey)
        else
          acceptForWrite(sel, selectionKey)
      }
      sender ! "OK"


    case selectionKey: SelectionKey if selectionKey.isValid && selectionKey.isReadable =>
      if (writeQueue.headOption.isDefined && writeQueue.head._2 == selectionKey.socketChannelLocalPort) {
        prepareForWrite(selectionKey)
      } else {
        read(selectionKey)
        logger.trace("Read from selectionKey... " +selectionKey.socketChannelLocalPort)
      }


    case selectionKey: SelectionKey if selectionKey.isValid && selectionKey.isWritable && writeQueue.headOption.isDefined =>
      logger.info("Write to channel...?")
      val (_, port) = writeQueue.head
      val maybePort = selectionKey.socketChannelLocalPort
      if (maybePort == port) {
        val dataToWrite = writeQueue.dequeueAll(_._2 == port)

        logger.info("Write to selectionKey...")
        writeAll(dataToWrite.map(_._1), selectionKey)
      }


    case selectionKey: SelectionKey if selectionKey.isValid && selectionKey.isWritable =>
      // writeable, but nothing to write


    case selectionKey: SelectionKey =>
      selectionKey.channel().close()
      selectionKey.cancel()
  }



  def prepareForWrite(selKey: SelectionKey) {
    monitor.registerInterestOpsChange(selKey, SelectionKey.OP_WRITE)
  }

  def handleReadData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long) {
    logger.info("Sending read data to metric actor...")
    metricHandlerActor ! DataReceived(socketChannel, data.take(bytes.toInt))
  }
}
