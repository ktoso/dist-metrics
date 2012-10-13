#!/usr/bin/env python

import socket
import httplib, urllib

import pb.common_pb2 as MetricType
from pb.subscribe_pb2 import SubscribeRequest, SubscriptionResponse


MONITOR_HOST = "127.0.0.1"
MONITOR_HTTP_PORT = 8080
MONITOR_HTTP_URL = MONITOR_HOST + ":" + str(MONITOR_HTTP_PORT)

TCP_IP = '127.0.0.1'
TCP_PORT = 4444
BUFFER_SIZE = 1024
MESSAGE = "Hello, World!"

# register
def register(resourceId, metricType):
  conn = httplib.HTTPConnection(MONITOR_HTTP_URL)

  rq = SubscribeRequest()
  rq.resourceId = resourceId
  if metricType == "cpu":
    rq.metricType = MetricType.Cpu
  elif metricType == "memfree":
    rq.metricType = MetricType.MemFree
  
  conn.request("POST", "/subscriptions", rq.SerializeToString())

  response = conn.getresponse()
  print response.status, response.reason
  data = response.read()
  print data
  rs = SubscriptionResponse()
  rs.ParseFromString(data)
  data.close()



#s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#s.connect((TCP_IP, TCP_PORT))
#s.send(MESSAGE)
#data = s.recv(BUFFER_SIZE)
#s.close()
#
#print "received data:", data

def main():
  print "Welcome to dist-metrics client!"
  # register(raw_input("Register to host's messages: "))
  register("moon", "memfree")

if __name__ == '__main__':
  main()
