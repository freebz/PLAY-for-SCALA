// 목록 9.4  app/com/github/playforscala/barcodes/BarcodeCache.scala

package com.github.playforscala.barcodes

import akka.actor.Actor
import concurrent._
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
import org.krysalis.barcode4j.impl.upcean.EAN13Bean
import scala.util.Try
import play.api.libs.concurrent.Execution.Implicits._

class BarcodeCache extends Actor {

  var imageCache = Map[Long, Future[Array[Byte]]]()

  def receive = {
    case RenderImage(ean) => {
      val futureImage = imageCache.get(ean) match {

        case Some(futureImage) => futureImage
        case None => {
          val futureImage = future { ean13BarCode(ean, "image/png") }
          imageCache += (ean -> futureImage)
          futureImage
        }
      }

      val client = sender
      futureImage.onComplete {
        client ! RenderResult(_)
      }
    }
  }
}
