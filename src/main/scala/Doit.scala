package doit

import akka.actor._
import akka.util.duration._

object Doit extends App {

  val system = ActorSystem("DoitSystem")

  val tickActor = system.actorOf(Props[TickActor], name="tick")

  //This will schedule to send the Tick-message
  //to the tickActor after 0s repeating every second

  val cancellable =
    system.scheduler.schedule(0 seconds,
      1 seconds,
      tickActor,
      "tick")
}
