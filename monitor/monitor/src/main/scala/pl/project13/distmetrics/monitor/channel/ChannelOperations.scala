package pl.project13.distmetrics.monitor.channel

import java.nio.channels.{SocketChannel, Selector, SelectionKey}
import pl.project13.distmetrics.monitor.util.JavaNioConversions
import java.nio.ByteBuffer
import scalaz.Scalaz._
import com.google.protobuf.GeneratedMessage
import com.weiglewilczek.slf4s.Logging
import java.io.IOException

trait ChannelOperations extends JavaNioConversions with Logging {

  def selector: Selector

  val readBuffer = ByteBuffer.allocate(8192)

  def handleData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long)

  def accept(key: SelectionKey) {
    // For an accept to be pending the channel must be a server socket channel.
    val serverSocketChannel = key.serverSocketChannel

    // Accept the connection and make it non-blocking
    val socketChannel = serverSocketChannel.accept()
    val socket = socketChannel.socket()
    socketChannel.configureBlocking(false)

    // Register the new SocketChannel with our Selector, indicating
    // we'd like to be notified when there's data waiting to be read
    socketChannel.register(this.selector, SelectionKey.OP_READ)
  }

  def read(key: SelectionKey) {
    val socketChannel = key.socketChannel

    // Clear out our read buffer so it's ready for new data
    readBuffer.clear()

    // Attempt to read off the channel
    var numRead = 0
    try {
      numRead = socketChannel.read(this.readBuffer)

      if (numRead == -1) {
        // Remote entity shut the socket down cleanly. Do the
        // same from our end and cancel the channel.
        key.channel().close()
        key.cancel()
        return
      }

      // Hand the data off to our worker thread
      handleData(socketChannel, this.readBuffer.array(), numRead)
    } catch {
      case ex: IOException =>
        // The remote forcibly closed the connection, cancel
        // the selection key and close the channel.
        key.cancel()
        socketChannel.close()
        return
    }

    def writeToChannel(content: Array[Byte], key: SelectionKey) {
      val bytesCount = content.size
      if (key.isValid && key.isWritable) {
        val channel = key.socketChannel
        val wrote = channel.write(ByteBuffer.wrap(content))

        if (wrote /== bytesCount)
          throw new Exception("Wasn't able to write all bytes to channel! [%s] to write, but wrote [%s]".format(bytesCount, wrote))
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
}
