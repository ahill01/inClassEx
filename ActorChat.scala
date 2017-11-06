package actors

import scala.concurrent.duration._

import akka.actor.ActorSystem
import akka.actor.Props
import java.net.ServerSocket
import java.io.PrintStream
import java.io.BufferedReader
import java.io._
import scala.concurrent.Future

object ActorChat extends App {
  val system = ActorSystem("Chatting")
  implicit val ec = system.dispatcher

  val chatSuper = system.actorOf(Props[ChatSupervisor], "chatSuper")

  system.scheduler.schedule(0.seconds, 0.1.seconds, chatSuper, ChatSupervisor.CheckInputs)

  val ss = new ServerSocket(12345)
  while (true) {
    val sock = ss.accept()
    val ps = new PrintStream(sock.getOutputStream)
    ps.println("What's your name?") //need outputStream of user's computer, //want print stream
    Future {
      val br = new BufferedReader(new InputStreamReader((sock.getInputStream())))
      val name = br.readLine() //another blocking call, waits to get a name! -> but blocks ever1 else in queue until person before them types in name
      // can't happen in main thread ^ -> wrap in future
      println("Welcome, " + name + "!")
      chatSuper ! ChatSupervisor.NewChatter(name, sock, ps, br) //when som1 connects, makes new Chatter
    }
  } //ok for a blocking call here b/c actors! 
}