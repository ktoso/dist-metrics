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
import akka.actor.{Actor, ActorRef, Props, ActorSystem}
import pl.project13.distmetrics.monitor.actor._
import pl.project13.distmetrics.monitor.spray.{MonitorServicesModule, SubscriptionsService}
import cc.spray.{SprayCanRootService, RootService, HttpService}
import cc.spray.io.IoWorker
import cc.spray.can.server.HttpServer
import cc.spray.io.pipelines.MessageHandlerDispatch
import akka.pattern._
import akka.dispatch.Await
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy._
import akka.util.{Timeout, Duration}
import akka.util.duration._
import java.util.concurrent._
import com.google.common.util.concurrent.{ListenableFutureTask, SettableFuture, ThreadFactoryBuilder}
import scala.collection._
import pl.project13.distmetrics.monitor.actor.ChannelInformation
import pl.project13.distmetrics.monitor.actor.DataReceived

trait MonitorMain extends Logging
  with ChannelOperations {

  def config: MonitorConfig

  val selector = Selector.open()

  def selectionRouterActor: ActorRef

  implicit val atMost = 30.seconds
  implicit val timeout = Timeout(atMost)

  val registeredChannels = new AtomicInteger(0)

  private val interestOpChanges = mutable.Queue[() => Unit]()

  /**
   * As channel registration has be on the thread the selectr was created on,
   * we use this queue to create tasks for this thread. See also `registerNewChannels`.
   */
  private val channelsToRegister = mutable.Queue[() => SelectionKey]()

  @tailrec final def loop() {
    selector.select(100.millis.toMillis)
    changeInterestSets(interestOpChanges)
    registerNewChannels(channelsToRegister)

    val selKeysIterator = selector.selectedKeys.iterator
    selKeysIterator foreach { key =>
      try {
        selKeysIterator.remove()

        selectionRouterActor ! key
      } catch {
        case ex: Exception =>
          key.cancel()
          logger.error("Got IO exception while processing. Channel cancelled.", ex)
      }
    }

    loop()
  }

  @tailrec
  private final def registerNewChannels(toRegister: mutable.Queue[() => SelectionKey]) {
    toRegister.dequeueFirst(_ => true) match {
      case Some(register) =>
        register()
        registerNewChannels(toRegister)

      case None => ()
    }
  }

//  @tailrec
  private final def changeInterestSets(requests: mutable.Queue[() => Unit]) {
    requests.dequeueFirst(_ => true) match {
      case Some(changeInterestSet) =>
        logger.info("Change interest set..." + requests.size)
        changeInterestSet()
//        changeInterestSets(requests)

      case None  => ()
    }
  }

  def registerInterestOpsChange(selectionKey: SelectionKey, ops: Int) {
    interestOpChanges enqueue { () =>
      val socket = selectionKey.socketChannel
      socket.keyFor(this.selector).interestOps(ops)
    }
  }

  def registerForAccept(channel: ServerSocketChannel, writeable: Boolean, blockingCreate: Boolean = true): SelectionKey = {
    logger.info("Will enqueue socket channel to be registered...")
    val selectionKeyFuture = SettableFuture.create[SelectionKey]

    // enqueue to be processed on the servers selector thread (see NIO docs for channel register)
    channelsToRegister enqueue { () =>
      val selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT)
      logger.info("Registered new socket channel with selector")

      selectionKeyFuture.set(selectionKey)
      selectionKey
    }

    if (blockingCreate)
      selectionKeyFuture.get(30.seconds.toMillis, TimeUnit.MILLISECONDS)
    else
      null
  }

  /**
   * Important NIO note: Registration of a new channel must be done from the same thread that has created the selector.
   * This method takes care of this.
   *
   * Requires server `loop` to be running.
   *
   * @return (opened port was this? the subscription id, which port was opened)
   */
  def openNewChannel(writeable: Boolean, blockingCreate: Boolean = true) = {
    val openChan = registeredChannels.getAndIncrement()
    val portToUse = config.sensorPort + openChan

    logger.info("Opening channel in [%s]...".format(portToUse))
    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.configureBlocking(false)
    serverSocketChannel.socket().bind(portToUse)

    val selectionKey = registerForAccept(serverSocketChannel, writeable, blockingCreate)

    logger.info("Opened new channel on [%s]. Already [%s] channels open".format(portToUse, registeredChannels.get))
    ChannelInformation(openChan, selectionKey)
  }
}

trait MonitorActorSystem extends Logging {
  this: MonitorMain =>

  logger.info("Starting actor system...")

  val system = ActorSystem("monitor-system")

  logger.info("Starting handler actors ...")
  val subscriptionActor = system.actorOf(Props(new ClientSubscriptionActor(this, config)), name = "subscription-handler")
  val sensorMeasurementActor = system.actorOf(Props(new SensorMeasurementActor(subscriptionActor)), name = "measurement-handler")
  val selectionRouterActor = system.actorOf(Props( new SelectionRouterActor(this, sensorMeasurementActor, subscriptionActor)), name = "selection-handler")


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

  lazy val config = MonitorConfig

  selectionRouterActor ! selector

  sprayCanServer ! HttpServer.Bind(config.host, config.port)

  openNewChannel(writeable = false, blockingCreate = false)
  loop()

  override def handleReadData(socketChannel: SocketChannel, data: Array[Byte], bytes: Long) {
    sensorMeasurementActor ! DataReceived(socketChannel, data.take(bytes.toInt))
  }

}
