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
import akka.actor.{ActorRef, Props, ActorSystem}
import pl.project13.distmetrics.monitor.actor._
import pl.project13.distmetrics.monitor.spray.{MonitorServicesModule, SubscriptionsService}
import cc.spray.{SprayCanRootService, RootService, HttpService}
import cc.spray.io.IoWorker
import cc.spray.can.server.HttpServer
import cc.spray.io.pipelines.MessageHandlerDispatch
import akka.pattern._
import akka.dispatch.Await
import akka.util.{Timeout, Duration}
import akka.util.duration._

trait MonitorMain extends Logging
  with ChannelOperations {

  def config: MonitorConfig

  val selector = Selector.open()

  def selectionRouterActor: ActorRef

  implicit val atMost = 30.seconds
  implicit val timeout = Timeout(atMost)

  val openChannels = new AtomicInteger(0)

  @tailrec final def loop() {
    logger.info("Selecting...")
    selector.select()

    val selKeysIterator = selector.selectedKeys.iterator
    selKeysIterator foreach { key =>
      try {
        selKeysIterator.remove()

        selectionRouterActor ! key
        Thread.sleep(10)

//        if (!key.isValid) {
//          // ignore
//        } else if (key.isAcceptable) {
//          accept(selector, key)
//        } else if (key.isReadable) {
//          read(key)
//        } else if (key.isWritable) {
//          write(key)
//        } else {
//          throw new RuntimeException("Not implemented yet!") // TODO implement me
//        }
      } catch {
        case ex: Exception =>
          key.cancel()
          logger.error("Got IO exception while processing. Channel cancelled.", ex)
      }
    }

    loop()
  }

  /** @return (opened port was this? the subscription id, which port was opened) */
  def openNewChannel() = {
    val openChan = openChannels.getAndIncrement()
    val portToUse = config.sensorPort + openChan

    logger.info("Opening channel in [%s]...".format(portToUse))
    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.configureBlocking(false)
    serverSocketChannel.socket().bind(portToUse)

    val selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)

    logger.info("Opened new channel on [%s]. Already [%s] channels open".format(portToUse, openChannels.get))
    ChannelInformation(openChan - 1, selectionKey)
  }
}

trait MonitorActorSystem extends Logging {
  this: MonitorMain =>

  logger.info("Starting actor system...")

  val system = ActorSystem("monitor-system")

  logger.info("Starting handler actors ...")
  val sensorMeasurementActor = system.actorOf(Props[SensorMeasurementReceiverActor], name = "measurement-handler")
  val subscriptionActor = system.actorOf(Props(new ClientSubscriptionActor(this)), name = "subscription-handler")
  val selectionRouterActor = system.actorOf(Props( new SelectionRouterActor(sensorMeasurementActor, subscriptionActor)), name = "selection-handler")


  logger.info("Starting spray.cc ...")

  val mainModule = new MonitorServicesModule {
    implicit def actorSystem = system

    lazy val config = Main.config

    def subscriptionHandler = subscriptionActor

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

  selectionRouterActor ! selector

  sprayCanServer ! HttpServer.Bind(config.host, config.port)

  openNewChannel()

  loop()

  override def handleReadData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long) {
    sensorMeasurementActor ! DataReceived(socketChannel, data.take(bytes.toInt))
  }

}
