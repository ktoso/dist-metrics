package main

import (
	"log"

	"io"
	"net"
	"time"

	"./pb"
	"code.google.com/p/goprotobuf/proto"

	"github.com/kless/goconfig/config"
)

var c, _ = config.ReadDefault("config.conf")
var sensorHost, _ = c.String("sensor", "host")
var monitorHost, _ = c.String("monitor", "host")
var monitorPort, _ = c.Int("monitor", "port")

func Reader(r io.Reader) {
	buf := make([]byte, 1024)

	for {
		n, err := r.Read(buf[:])
		if err != nil {
			return
		}
		println("Client got:")
		println(string(buf[0:n]))
	}
}

func TestProtocolBuffers() {
	test := &distmetrics.Measurement{
		ResourceId: proto.String("moon"),
		MetricType: distmetrics.MetricType_Cpu.Enum(),
		Value:      proto.String("50"),
		Timestamp:  proto.Int32(111),
	}

	data, err := proto.Marshal(test)
	if err != nil {
		log.Fatal("marshaling error: ", err)
	}
	newTest := &distmetrics.Measurement{}
	err = proto.Unmarshal(data, newTest)
	if err != nil {
		log.Fatal("unmarshaling error: ", err)
	}
	// Now test and newTest contain the same data.
	if test.GetResourceId() != newTest.GetResourceId() {
		log.Fatalf("data mismatch %q != %q", test.GetResourceId(), newTest.GetResourceId())
	} else {
		log.Printf("Unmarshalled protocol buffer message: the resource id is: %q", newTest.GetResourceId())
	}
}

func main() {
	log.Printf("Host name configured as: %q", sensorHost)
	log.Printf("Monitor configured at:   %q:%d", monitorHost, monitorPort)

  //  dial to local monitor
  d, _ := net.Dial("tcp", "localhost:9900")
  d.Write([]byte("GET / HTTP/1.0\r\n\r\n"))
  d.Close()
  // end of local dial

	c, err := net.Dial("tcp", "google.com:80")

	if err != nil {
		println("dial error", err.Error())
		return
	}

	go Reader(c)
	for {
		_, err := c.Write([]byte("GET / HTTP/1.0\r\n\r\n"))
		if err != nil {
			println("unable to write!", err.Error())
			break
		}
		time.Sleep(1e9)
	}
}