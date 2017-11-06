package actors

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket
import akka.actor.Props

class ChatSupervisor extends Actor {
  import ChatSupervisor._
  def receive = {
    case CheckInputs =>
      context.children.foreach(_ ! Chatter.CheckInput)
    
    case NewChatter(name, sock, ps, br) =>
      val lname = name.filter(_.isLetterOrDigit)
      if(context.child(lname).isEmpty) {
      context.actorOf(Props(new Chatter(name, sock, ps, br)), name.filter(_.isLetterOrDigit))
      //make new Chatter, actorOf -> how make new actors
      } else {
        println("That name is already taken")
        sock.close()
      }
      
    case SendMessage(msg) =>
      context.children.foreach(_ ! Chatter.PrintOutput(msg))

    case _ => println("Oops in ChatSupervisor!")

  }
}
object ChatSupervisor {
  case object CheckInputs //not gonna pass arguments -> case object!
  case class NewChatter(name: String, sock: Socket, ps: PrintStream, br: BufferedReader) // has arguments -> case class!
  case class SendMessage(msg: String)
}