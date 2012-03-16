package doit

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

class TickActor extends Actor {

  val log = Logging(context.system, this)
  val driver = new FileDriver("tick.txt")

  def receive = {
    case "tick" => tick
  }

  def tick() = {
    driver tick

    //TODO: Could be cool to play a sound here
  }
}
