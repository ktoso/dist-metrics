#!/usr/bin/env python

import httplib
import socket

import time
import threading

import pb.common_pb2 as MetricType
from pb.measure_pb2 import Measurement
from pb.subscribe_pb2 import SubscribeRequest, SubscriptionResponse


MONITOR_HOST = "127.0.0.1"
MONITOR_HTTP_PORT = 8080
MONITOR_HTTP_URL = MONITOR_HOST + ":" + str(MONITOR_HTTP_PORT)

TCP_IP = '127.0.0.1'
TCP_PORT = 4444
BUFFER_SIZE = 18
MESSAGE = "Hello, World!"

# http, duh
GET = "GET"
POST = "POST"
DELETE = "DELETE"

class ThreadedClient(threading.Thread):
    def __init__(self, subscription_id, host, port):
        self.subscription_id = subscription_id
        self.host = host
        self.port = port
        self.running = True
        threading.Thread.__init__(self)
        self.daemon = False

    def stop(self):
        delete_subscription(self.subscription_id)
        self.running = False

    def run(self):
        print("Reading from {}:{}".format(self.host, self.port))
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        # check and turn on TCP Keepalive
        x = s.getsockopt( socket.SOL_SOCKET, socket.SO_KEEPALIVE)
        if not x:
            print 'Socket Keepalive off, turning on'
            x = s.setsockopt( socket.SOL_SOCKET, socket.SO_KEEPALIVE, 1)
            print 'setsockopt = ', x
        else:
            print 'Socket Keepalive already on'

        s.connect((self.host, self.port))

        while self.running:
            message = s.recv(BUFFER_SIZE)
            m = Measurement()
            m.ParseFromString(message)
            print "Got measurement: ", m
        s.close()

def delete_subscription(subscription_id):
    print
    subscription_path = "/subscriptions/{}".format(subscription_id)
    print "requesting:", DELETE, MONITOR_HTTP_URL, subscription_path

    conn = httplib.HTTPConnection(MONITOR_HTTP_URL)
    conn.request(DELETE, "http://" + MONITOR_HTTP_URL + subscription_path)
    response = conn.getresponse()
    print "response status: ", response.status, response.reason


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
    print "requesting:", GET, resource_uri
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


def subscribe_for_measurements(subscription_response):
    subscription_id = subscription_response.subscriptionId
    host = subscription_response.host
    port = subscription_response.port
    client = ThreadedClient(subscription_id, host, port)
    client.start()
    return client


def register(resource_id, metric_key):
    resource_uri = request_subscription_resource_location(resource_id, metric_key)

    subscription_response = get_registration_port(resource_uri)

    client = subscribe_for_measurements(subscription_response)

    return client


def metric_key_as_enum(key):
    if key == "cpu":
        return MetricType.Cpu
    elif key == "memfree":
        return MetricType.MemFree
    elif key == "memused":
        return MetricType.MemUsed
    else:
        raise NameError("Invalid metric key! Valid: cpu, memfree, memused")



def main():
    print "Welcome to dist-metrics client!"
    #    manual registration
    #    resourceId = raw_input("Register to host's messages: ")
    #    metricKey = raw_input("What metric? [cpu, memfree, memused]: ")
    #    register(resourceId, metricKey)

    #   auto registration
    client = register("moon", "cpu")

    raw_input("To [quit] press enter...\n\n")

    print "Stopping clients..."
    client.stop()
    exit(0)

if __name__ == '__main__':
    main()
