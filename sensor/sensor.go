package main

import (
	"log"

	"fmt"
	"math/rand"
	"net"
	"time"

	"./pb"
	"code.google.com/p/goprotobuf/proto"

	"github.com/kless/goconfig/config"
)

var c, _ = config.ReadDefault("config.conf")
var sensorHost, _ = c.String("sensor", "host")
var sensorDelayRaw, _ = c.String("sensor", "delay")
var sensorDelay, _ = time.ParseDuration(sensorDelayRaw)
var monitorHost, _ = c.String("monitor", "host")
var monitorPort, _ = c.Int("monitor", "port")

func main() {
	log.Printf("Host name configured as: %s", sensorHost)
	log.Printf("Monitor configured at:   %s:%d", monitorHost, monitorPort)
	log.Printf("Will sleep %s between sending stats", sensorDelayRaw)

	//  dial to local monitor
	// go Reader(c)

	for {
		metric := &distmetrics.Measurement{
			ResourceId: proto.String(sensorHost),
			MetricType: distmetrics.MetricType_Cpu.Enum(),
			Value:      proto.String(fmt.Sprintf("%d", rand.Intn(101))),
			Timestamp:  proto.Int64(time.Now().Unix()),
		}

		c, err := net.Dial("tcp", fmt.Sprintf("%s:%d", monitorHost, monitorPort))
		if err != nil {
			println("dial error", err.Error())
			return
		}

    println("send measurement: ", metric.String())
		data, _ := proto.Marshal(metric)
		_, err = c.Write(data)

		if err != nil {
			println("write error", err.Error())
			return
		}

		// c.SetKeepAlive(true)
		c.Close()

		time.Sleep(sensorDelay)
	}

}
