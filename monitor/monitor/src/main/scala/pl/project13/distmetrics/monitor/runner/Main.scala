package pl.project13.distmetrics.monitor.runner

import java.nio._
import channels._
import pl.project13.distmetrics.monitor.config.MonitorConfig
import com.weiglewilczek.slf4s.Logging
import collection.JavaConversions._
import annotation.tailrec
import java.util.concurrent.atomic.AtomicInteger
import pl.project13.distmetrics.monitor.channel.ChannelOperations
import java.io.IOException
import akka.actor.{Props, ActorSystem}
import pl.project13.distmetrics.monitor.actor._
import pl.project13.distmetrics.monitor.spray.{MonitorServicesModule, SubscriptionsService}
import cc.spray.{SprayCanRootService, RootService, HttpService}
import cc.spray.io.IoWorker
import cc.spray.can.server.HttpServer
import cc.spray.io.pipelines.MessageHandlerDispatch

trait MonitorMain extends Logging
  with ChannelOperations {

  def config: MonitorConfig

  override val selector = Selector.open()

  val openChannels = new AtomicInteger(0)

  @tailrec final def loop() {
    logger.info("Selecting...")
    selector.select()

    val selKeysIterator = selector.selectedKeys.iterator
    selKeysIterator foreach {
      key =>
        try {
          selKeysIterator.remove()

          if (!key.isValid) {
            // ignore
          } else if (key.isAcceptable) {
            accept(key)
          } else if (key.isReadable) {
            read(key)
          } else if (key.isWritable) {

          } else {
            throw new RuntimeException("Not implemented yet!") // TODO implement me
          }
        } catch {
          case ex: Exception =>
            key.cancel()
            logger.error("Got IO exception while processing. Channel cancelled.", ex)
        }
    }

    loop()
  }

  def openNewChannel() {
    val portToUse = config.port + openChannels.getAndIncrement()

    logger.info("Opening channel in [%s]...".format(portToUse))
    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.configureBlocking(false)
    serverSocketChannel.socket().bind(portToUse)

    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)

    logger.info("Opened new channel on [%s]. Already [%s] channels open".format(portToUse, openChannels.get))
  }
}

trait MonitorActorSystem extends Logging {
  logger.info("Starting actor system...")

  val system = ActorSystem("monitor-system")

  logger.info("Starting handler actors ...")
  val subscriptionHandler = system.actorOf(Props[SubscriptionHandlerActor], name = "subscription-handler")

  logger.info("Starting spray.cc ...")

  val mainModule = new MonitorServicesModule {
    implicit def actorSystem = system

    lazy val config = Main.config

    val services = rootService ~ subscriptionsService // combine all services
  }

  val httpService = system.actorOf(
    Props(new HttpService(mainModule.services)),
    name = "subscriptions-service"
  )
  val rootService = system.actorOf(
    Props(new SprayCanRootService(httpService)),
    name = "spray-can-root-service"
  )

  // shared low level io worker
  val ioWorker = new IoWorker(system).start()

  val sprayCanServer = system.actorOf(
    Props(new HttpServer(ioWorker, MessageHandlerDispatch.SingletonHandler(rootService))),
    name = "http-server"
  )

  // general
  system.registerOnTermination {
    logger.info("Application shut down...")
    ioWorker.stop()
  }

}

object Main extends App with MonitorMain with MonitorActorSystem {

  val config = MonitorConfig

  sprayCanServer ! HttpServer.Bind(config.host, config.port)

//  openNewChannel()

  loop()

  // todo refactor somehow
  override def handleData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long) {
    subscriptionHandler ! DataReceived(this, socketChannel, data)
  }
}
