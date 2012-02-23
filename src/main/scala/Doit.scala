package doit

import akka.actor._
import akka.util.duration._

import scala.tools.util.SignalManager

object Doit extends App {

  val system = ActorSystem("DoitSystem")

  val tickActor = system.actorOf(Props[TickActor], name = "tick")

  //This will schedule to send the Tick-message
  //to the tickActor after 0s repeating every second

  system.scheduler.schedule(
    0 seconds,
    1 seconds,
    tickActor,
    "tick")

  system.scheduler.scheduleOnce(
    10 seconds,
    tickActor,
    PoisonPill)


  Seq("TERM", "INT") map { sig =>
    SignalManager(sig) = {
      env.logger("[SIG%s] Asking the reaper to terminate nicely..." format sig)
      self ! PoisonPill
    }
  }

}
