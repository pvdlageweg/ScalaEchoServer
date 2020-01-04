package com.pvdlageweg

import akka.actor.{ Actor, Terminated }

class EchoActor extends Actor {
  import akka.io.Tcp._

  override def receive = {
    case Received(data) =>
      sender() ! Write(data)
    case PeerClosed | Terminated | CommandFailed =>
      context stop self
  }
}