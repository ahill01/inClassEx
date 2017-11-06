package utility

import scalafx.application.JFXApp
import utility.Complex
import scalafx.scene.image.WritableImage
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.paint.Color
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform

object Mandelbrot extends JFXApp {
  val MaxCount = 10000

  stage = new JFXApp.PrimaryStage {
    title = "Mandelbrot"
    scene = new Scene(800, 800) {
      val img = new WritableImage(800, 800)
      val iv = new ImageView(img)
      content = iv

      Future {
        fillMandelImage(-1.5, 0.5, -1.0, 1.0, img)
      }
    }
  }

  def fillMandelImage(rmin: Double, rmax: Double, imin: Double, imax: Double, img: WritableImage): Unit = {
      val start = System.nanoTime()
      val w = img.width().toInt
      val h = img.height().toInt
      val pw = img.pixelWriter
      val lmax = math.log(MaxCount)
      for (i <- (0 until w).par) {
        println(i)
        val real = rmin + i * (rmax - rmin) / h
        val colors = for (j <- 0 until h) yield {
          val imag = imin + j * (imax - imin) / h
          val cnt = mandelCount(Complex(real, imag))
          val lscale = math.log(cnt) / lmax
          j -> (if (cnt >= MaxCount) Color.Black else Color(0.0, lscale, 0.0, 1.0))
        }
        Platform.runLater {
          for((j, c) <- colors) pw.setColor(i, j, c)
        }
      }
      println((System.nanoTime() - start) * 1e-9)
  }

  def mandelCount(c: Complex): Int = {
    var cnt = 0
    var z = c
    while (z.mag < 4 && cnt < MaxCount) {
      z = z * z + c
      cnt += 1
    }
    cnt
  }
}