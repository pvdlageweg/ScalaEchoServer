
package com.pvdlageweg

import akka.actor.{ ActorSystem, Props }
import akka.io.Tcp.{ Received, Write }
import akka.testkit.{ ImplicitSender, TestKit }
import akka.util.ByteString
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }

class EchoActorSpec extends TestKit(ActorSystem("MySpec")) with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "An Echo actor" must {
    "send back messages unchanged" in {
      val echoActor = system.actorOf(Props[EchoActor], name = "echoActor")
      val data = ByteString("hello world")
      echoActor ! Received(data)
      expectMsg(Write(data))
    }

  }
}
