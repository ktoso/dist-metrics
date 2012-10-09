package pl.project13.distmetrics.monitor.channel

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import pl.project13.distmetrics.common.proto.{Subscribe, Common}

class ChannelOperationsTest extends FlatSpec with ShouldMatchers
  with ChannelOperations {

  it should "parse a proto message" in {
    // given
    val subscribe = Subscribe.SubscribeRequest.newBuilder
      .setMetricType(Common.MetricType.Cpu)
      .setResourceId("dynames")
      .build()

    val bytes = subscribe.toByteArray

    // when
    val parsed = Subscribe.SubscribeRequest.parseFrom(bytes)

    // then
    parsed.getResourceId should equal ("dynames")
    parsed.getMetricType should equal (Common.MetricType.Cpu)
  }
}
