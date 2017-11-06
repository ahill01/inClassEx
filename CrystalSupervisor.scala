package actors

import akka.actor.Actor
import scalafx.scene.image.WritableImage
import akka.actor.Props

class CrystalSupervisor(wi:WritableImage) extends Actor {
 import CrystalSupervisor._
 for(i <- 1 to 100) {
   context.actorOf(Props[Floaty], "Floaty"+i)
   //when we create a new Crystal Supervisor, this code gets run
 } 
 
 def receive = {
    case Update => println("Updating")
    for (f <- context.children) f ! Floaty.Float
 
    case CheckPosition(x,y) => 
      //check bounds
      //check if stuck
      //if stuck tell floaty to reset, add a pixel
    
    case _ => println("Oops")
    Thread.dumpStack() //tells you where things went wrong! 
  }
}

object CrystalSupervisor {
  case object Update //send message to all Floaty, tell them to move
  case class CheckPosition(x:Int, y:Int)
}