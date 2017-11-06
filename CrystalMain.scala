package actors

import scalafx.application.JFXApp
import akka.actor.ActorSystem
import akka.actor.Props
import scalafx.scene.image.WritableImage
import scala.concurrent.duration._
import scalafx.scene.Scene
import scalafx.scene.image.ImageView

object CrystalMain extends JFXApp {
  val system = ActorSystem("Crystals")
  println("testing")
  
  val wi = new WritableImage(600, 600)
  val cs = system.actorOf(Props(new CrystalSupervisor(wi)), "CrystalSuper")
  import system.dispatcher
  
  println("Schedule event.")
  system.scheduler.schedule(0.seconds, 0.1.seconds, cs, CrystalSupervisor.Update)
  
  stage = new JFXApp.PrimaryStage {
    title = "Crystals!"
    scene = new Scene(600, 600) {
      val iv = new ImageView(wi)
      content = iv
    }
  }
}