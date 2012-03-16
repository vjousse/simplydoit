package doit

import akka.actor._
import akka.util.duration._

case class Doit(env: Env) {

  def startSession: Unit = {

    val system = ActorSystem("DoitSystem")
    val tickActor = system.actorOf(Props[TickActor], name = "tick")

    //This will schedule to send the Tick-message
    //to the tickActor after 0s repeating every second

    system.scheduler.schedule(
      //inital delay
      0 seconds,
      //frequency
      env.tickFrequency seconds,
      tickActor,
      "tick")

    system.scheduler.scheduleOnce(
      env.sessionSeconds seconds,
      tickActor,
      PoisonPill)

  }
}
