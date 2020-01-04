package com.pvdlageweg

import java.net.InetSocketAddress

import akka.actor.{ Actor, ActorRef, actorRef2Scala }
import akka.io.{ IO, Udp }

class EchoServerUdp(listenAddress: InetSocketAddress) extends Actor {

  import context.system

  IO(Udp) ! Udp.Bind(self, listenAddress)

  def receive = {
    case Udp.Bound(local) =>
      context.become(ready(sender()))
  }

  def ready(socket: ActorRef): Receive = {
    case Udp.Received(data, remote) =>
      socket ! Udp.Send(data, remote)
  }

}