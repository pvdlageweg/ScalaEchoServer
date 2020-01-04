package com.pvdlageweg

import java.net.InetSocketAddress

import akka.actor.{ Actor, Props }
import akka.io.{ IO, Tcp }

class EchoServerTcp(listenAddress: InetSocketAddress) extends Actor {

  import Tcp._
  import context.system

  IO(Tcp) ! Tcp.Bind(self, listenAddress)

  def receive = {
    case b @ Tcp.Bound(localAddress) =>
      context.parent ! b

    case Tcp.Connected(remote, local) =>
      val handler = context.actorOf(Props[EchoActor])
      sender() ! Register(handler)
  }
}