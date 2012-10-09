package pl.project13.distmetrics.monitor.channel

import java.nio.channels.{SocketChannel, SelectionKey}
import pl.project13.distmetrics.monitor.util.JavaNioConversions
import java.nio.ByteBuffer
import scalaz.Scalaz._
import com.google.protobuf.GeneratedMessage
import com.weiglewilczek.slf4s.Logging

trait ChannelOperations extends JavaNioConversions with Logging {

  // todo handle exceptions

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
        case n  =>
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
