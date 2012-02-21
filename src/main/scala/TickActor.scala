package doit

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

class TickActor extends Actor {

  val log = Logging(context.system, this)
  def receive = {
    case "tick" => println("Tick") //Do something
  }
}
