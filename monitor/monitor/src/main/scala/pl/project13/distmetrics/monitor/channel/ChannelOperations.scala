package pl.project13.distmetrics.monitor.channel

import java.nio.channels.{SocketChannel, Selector, SelectionKey}
import pl.project13.distmetrics.monitor.util.JavaNioConversions
import java.nio.ByteBuffer
import com.google.protobuf.GeneratedMessage
import com.weiglewilczek.slf4s.Logging
import java.io.IOException

trait ChannelReadOperation extends JavaNioConversions with Logging {

  private lazy val readBuffer = ByteBuffer.allocate(8192)

  def handleReadData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long)

  def read(key: SelectionKey) {
    val socketChannel = key.socketChannel

    // Clear out our read buffer so it's ready for new data
    readBuffer.clear()

    // Attempt to read off the channel
    var numRead = 0
    try {
      numRead = socketChannel.read(this.readBuffer)

      logger.info("Read [%s] bytes".format(numRead))

      if (numRead == -1) {
        // Remote entity shut the socket down cleanly. Do the
        // same from our end and cancel the channel.
        key.channel().close()
        key.cancel()
        return
      }

      // Hand the data off to our worker thread
      handleReadData(socketChannel, this.readBuffer.array(), numRead)
    } catch {
      case ex: IOException =>
        // The remote forcibly closed the connection, cancel
        // the selection key and close the channel.
//        logger.error("Closing connection", ex)
//        key.cancel()
//        socketChannel.close()
    }
  }

  def readFromChannel[A <: GeneratedMessage](key: SelectionKey)(build: Array[Byte] => A): Option[A] = {
    val buf = ByteBuffer.allocateDirect(1024)

    if (key.isValid && key.isReadable) {
      buf.clear()
      val channel = key.socketChannel

      val allBytes = collection.mutable.ListBuffer[Byte]()
      channel.read(buf) match {
        case -1 => channel.close()
        case n =>
          buf.flip()
          allBytes ++= buf.array().take(n)
      }

      try {
        val parsed = build(allBytes.toArray)
        Some(parsed)
      } catch {
        case ex: Exception =>
          logger.error("Unable to parse proto message!", ex)
          None
      }

    } else {
      None
    }
  }
}

trait ChannelWriteOperation extends JavaNioConversions with Logging {

  def write(content: Array[Byte], key: SelectionKey) {
    val socketChannel = key.socketChannel

    // Write until there's not more data ...
    //         while (!queue.isEmpty()) {
    val buf = ByteBuffer.wrap(content)
    socketChannel.write(buf)
    //           if (buf.remaining() > 0) {
    //             // ... or the socket's buffer fills up
    //             break;
    //           }
    //           queue.remove(0);
    //         }
    //
    //         if (queue.isEmpty()) {
    // We wrote away all data, so we're no longer interested
    // in writing on this socket. Switch back to waiting for
    // data.
    key.interestOps(SelectionKey.OP_READ)
    //         }
  }

  def writeAll(contents: Seq[Array[Byte]], key: SelectionKey) = try {
    val socketChannel = key.socketChannel

    contents foreach { content =>
      val buf = ByteBuffer.wrap(content)
      socketChannel.write(buf)
      socketChannel.close()
    }
    //           if (buf.remaining() > 0) {
    //             // ... or the socket's buffer fills up
    //             break;
    //           }
    //           queue.remove(0);
    //         }

    key.interestOps(SelectionKey.OP_READ)
  } catch {
    case ex: IOException =>
      logger.warn("Had to close channel during write. Probably client disconnected.")
      key.channel().close()
      key.cancel()
  }
}

trait ChannelAcceptOperation extends JavaNioConversions with Logging {

  def acceptForRead(selector: Selector, key: SelectionKey) {
    // For an accept to be pending the channel must be a server socket channel.
    val serverSocketChannel = key.serverSocketChannel

    // Accept the connection and make it non-blocking
    serverSocketChannel.accept() match {
      case null =>
      // already non-blocking and configured

      case socketChannel =>
        val socket = socketChannel.socket()
        socketChannel.configureBlocking(false)

        // Register the new SocketChannel with our Selector, indicating
        // we'd like to be notified when there's data waiting to be read
        socketChannel.register(selector, SelectionKey.OP_READ)
    }
  }

  def acceptForWrite(selector: Selector, key: SelectionKey) {
    // For an accept to be pending the channel must be a server socket channel.
    val serverSocketChannel = key.serverSocketChannel

    // Accept the connection and make it non-blocking
    serverSocketChannel.accept() match {
      case null =>
      // already non-blocking and configured

      case socketChannel =>
        val socket = socketChannel.socket()
        socketChannel.configureBlocking(false)

        // Register the new SocketChannel with our Selector, indicating
        // we'd like to be notified when there's data waiting to be read
        socketChannel.register(selector, SelectionKey.OP_WRITE)
    }
  }
}

trait ChannelOperations
  extends Logging
  with ChannelAcceptOperation
  with ChannelReadOperation
  with ChannelWriteOperation