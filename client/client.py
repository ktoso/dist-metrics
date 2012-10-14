#!/usr/bin/env python

import httplib

import pb.common_pb2 as MetricType
from pb.subscribe_pb2 import SubscribeRequest


MONITOR_HOST = "127.0.0.1"
MONITOR_HTTP_PORT = 8080
MONITOR_HTTP_URL = MONITOR_HOST + ":" + str(MONITOR_HTTP_PORT)

TCP_IP = '127.0.0.1'
TCP_PORT = 4444
BUFFER_SIZE = 1024
MESSAGE = "Hello, World!"

# http, duh
GET = "GET"
POST = "POST"
DELETE = "DELETE"

def request_subscription_resource_location(resource_id, metric_key):
    conn = httplib.HTTPConnection(MONITOR_HTTP_URL)

    rq = SubscribeRequest()
    rq.resourceId = resource_id
    rq.metricType = metric_key_as_enum(metric_key)

    conn.request(POST, "/subscriptions", rq.SerializeToString())

    response = conn.getresponse()
    resource_uri = response.getheader("Location")
    print "response status: ", response.status, response.reason
    print "response Location: ", resource_uri
    conn.close()

    return resource_uri

def get_registration_port(resource_uri):
    print "requesting: ", resource_uri
    host_port, path = resource_uri.split('/', 1)
    conn = httplib.HTTPConnection(host_port)

    conn.request(GET, "/" + path)
    response = conn.getresponse()
    print "response status:", response.status, response.reason
    print "response body: ", response.read()

    conn.close()
    return

# register
def register(resource_id, metric_key):
    resource_uri = request_subscription_resource_location(resource_id, metric_key)

    get_registration_port(resource_uri)

    # get response proto

#  rs = SubscriptionResponse()
#  rs.ParseFromString(data)
#  print ""

def metric_key_as_enum(key):
    if key == "cpu":
        return MetricType.Cpu
    elif key == "memfree":
        return MetricType.MemFree
    elif key == "memused":
        return MetricType.MemUsed
    else:
        raise NameError("Invalid metric key! Valid: cpu, memfree, memused")


#s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#s.connect((TCP_IP, TCP_PORT))
#s.send(MESSAGE)
#data = s.recv(BUFFER_SIZE)
#s.close()
#
#print "received data:", data

def main():
    print "Welcome to dist-metrics client!"
#    resourceId = raw_input("Register to host's messages: ")
#    metricKey = raw_input("What metric? [cpu, memfree, memused]")
#    register(resourceId, metricKey)
    register("moon", "memfree")

if __name__ == '__main__':
    main()
