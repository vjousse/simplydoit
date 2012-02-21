import akka.actor._
import akka.util.duration._

object Doit extends App {

  val system = ActorSystem("DoitSystem")

  val Tick = "tick"
  val tickActor = system.actorOf(Props(new Actor {
    def receive = {
      case Tick ⇒ println("Tick") //Do something
    }
  }))

  //This will schedule to send the Tick-message
  //to the tickActor after 0ms repeating every 50ms
  val cancellable =
    system.scheduler.schedule(0 seconds,
      1 seconds,
      tickActor,
      Tick)
}
