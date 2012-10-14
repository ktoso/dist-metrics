#!/usr/bin/env python

import httplib

import pb.common_pb2 as MetricType
from pb.measure_pb2 import Measurement
from pb.subscribe_pb2 import SubscribeRequest, SubscriptionResponse


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

listening = True

def request_subscription_resource_location(resource_id, metric_key):
    print
    print "requesting:", POST, MONITOR_HTTP_URL, "/subscriptions"
    conn = httplib.HTTPConnection(MONITOR_HTTP_URL)

    rq = SubscribeRequest()
    rq.resourceId = resource_id
    rq.metricType = metric_key_as_enum(metric_key)

    print "sending proto request: "
    print rq

    conn.request(POST, "/subscriptions", rq.SerializeToString())

    response = conn.getresponse()
    resource_uri = response.getheader("Location")
    print "response status: ", response.status, response.reason
    print "response Location: ", resource_uri
    conn.close()

    return resource_uri

def get_registration_port(resource_uri):
    print
    print "requesting:", POST, resource_uri
    host_port, path = resource_uri.split('/', 1)
    conn = httplib.HTTPConnection(host_port)

    conn.request(GET, "/" + path)
    response = conn.getresponse()
    data = response.read()
    print "response status:", response.status, response.reason

    rq = SubscriptionResponse()
    rq.ParseFromString(data)

    print "parsed proto response: "
    print rq

    conn.close()
    return rq

def subscribe_for_measurements(host, port):
#    raise BaseException("Implement me. \
#Should subscribe on a TCP connection on the given port, read it in a loop, \
#and print each proto recieved. The proto is [Measurement]")
    sock = socket.create_connection((host,port))
    while listening:
    	message = socket.recv(BUFFER_SIZE)
	m = Measurement()
      	m.ParseFromString(message)
      	print m
    sock.close()
#   todo figure out a way to not block the main thread, so you can "press enter to quit"


def register(resource_id, metric_key):
    resource_uri = request_subscription_resource_location(resource_id, metric_key)

    subscription_response = get_registration_port(resource_uri)

    subscribe_for_measurements(subscription_response.host, subscription_response.port)

    raw_input("press [enter] to _unsubscribe_ and _quit_")
    listening = False

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
#    manual registration
#    resourceId = raw_input("Register to host's messages: ")
#    metricKey = raw_input("What metric? [cpu, memfree, memused]: ")
#    register(resourceId, metricKey)

#   auto registration
    register("moon", "memfree")

if __name__ == '__main__':
    main()
