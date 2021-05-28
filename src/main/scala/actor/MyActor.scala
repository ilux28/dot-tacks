package actor

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

class MyActor extends  Actor {

  val log = Logging(context.system, this)

  override def receive: Receive = {
    case "test" => log.info("received test")
    case _ => log.info("received unknown message")
  }


}
