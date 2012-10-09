package pl.project13.distmetrics.monitor.util

import java.net.InetSocketAddress
import java.nio.channels.{ServerSocketChannel, SocketChannel, SelectionKey}

trait JavaNioConversions {

  implicit def richSelectionKey(key: SelectionKey) = new RichSelectionKey(key)
  class RichSelectionKey(key: SelectionKey) {
    def serverSocketChannel = key.channel.asInstanceOf[ServerSocketChannel]
    def socketChannel = key.channel.asInstanceOf[SocketChannel]
  }

  implicit def num2InetSocketAddres(in: Int) = new InetSocketAddress(in)
}
