package pl.project13.distmetrics.monitor.util

import java.net.InetSocketAddress
import java.nio.channels.{ServerSocketChannel, SocketChannel, SelectionKey}

trait JavaNioConversions {

  implicit def richSelectionKey(key: SelectionKey) = new RichSelectionKey(key)
  class RichSelectionKey(key: SelectionKey) {
    def serverSocketChannel = key.channel.asInstanceOf[ServerSocketChannel]
    def socketChannel = key.channel.asInstanceOf[SocketChannel]

    def serverSocketChannelLocalPort = serverSocketChannel.socket.getLocalPort
    def socketChannelLocalPort = socketChannel.socket.getLocalPort
  }

  implicit def num2InetSocketAddres(in: Int) = new InetSocketAddress(in)

  implicit def block2runnable(block: => Unit) = new Runnable {
    def run() {
      block
    }
  }
}
