package actors

import akka.actor.Actor

class Floaty(private var x: Int, private var y: Int) extends Actor {
  def receive = {
    case Float =>
      x += util.Random.nextInt(3) - 1 //floaty moves 
      y += util.Random.nextInt(2) //floaty moves (drifts down)
      sender ! CrystalSupervisor.CheckPosition(x, y) //not particularly efficient for actors

    case _ =>
      println("Floaty bad")
      Thread.dumpStack()
  }
}

object Floaty {
  //contains messages sent to Floaty

  case object Float
  case class ResetPosition(x: Int, y: Int)
}