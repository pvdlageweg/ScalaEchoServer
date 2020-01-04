package com.pvdlageweg

import java.net.InetSocketAddress

import akka.actor.{ ActorSystem, Props }

object EchoServerMain extends App {
  val listenAddress = new InetSocketAddress("localhost", 12345)
  val system = ActorSystem("echo-server-system")
  system.actorOf(Props(new EchoServerTcp(listenAddress)), name = "echo-tcp-server-system")
  system.actorOf(Props(new EchoServerUdp(listenAddress)), name = "echo-udp-server-system")
}