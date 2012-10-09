package pl.project13.distmetrics.monitor.actor

import akka.actor.Actor
import pl.project13.distmetrics.common.proto
import java.nio.channels.SocketChannel

class SubscriptionHandlerActor extends Actor {
  protected def receive = {
    case DataReceived(main, channel, data) =>
      val request = proto.Subscribe.SubscribeRequest.parseFrom(data)
  }
}
