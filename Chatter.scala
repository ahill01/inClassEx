package actors

import akka.actor.Actor
import java.io.PrintStream
import java.io.BufferedReader
import java.net.Socket

class Chatter(name: String, sock: Socket, out: PrintStream, in: BufferedReader) extends Actor {
  import Chatter._
  def receive = {

    case CheckInput =>
      if (in.ready()) {
        val msg = in.readLine()
        sender ! ChatSupervisor.SendMessage(name + " : " + msg)
        //send message to every1 else -> send to ChatSupervisor
      }

    case PrintOutput(msg) =>
      out.println(msg)

    case _ => println("Oops in Chatter!")
    //readLine is  blocking call -> make future OR in.ready() -> if something to read, returns true
  }
}
//chatter needs to know full name, socket, input stream, output stream
object Chatter {
  case object CheckInput
  case class PrintOutput(msg: String)
}