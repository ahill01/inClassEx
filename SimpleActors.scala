package actors
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props


object SimpleActors extends App {
  class SimpleActor extends Actor {
   def receive() = {
     case s:String => println("Got a string " + s)
     case i: Int => println("Got a number " + i)
     case _ => println("Oops! SimpleActor got something else!")
   }
 }
  val system = ActorSystem("SimpleActors") 
  val sa = system.actorOf(Props[SimpleActor], "Simpleton")
  
  sa ! "Hi"
  sa ! 42
  sa ! 3.14159
  
  Thread.sleep(1000) //prevents system from terminating before actor has a chance to respond
  system.terminate()
}