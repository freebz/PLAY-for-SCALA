// 목록 9.6  app/com/github/playforscala/barcodes/

package com.github.playforscala.barcodes

import play.api.mvc.{Action, Controller}
import util.{Failure, Success}
import play.api.libs.concurrent.Execution.Implicits._

class BarcodesController extends Controller {
  def barcode(ean: Long) = Action.async {
    Barcodes.renderImage(ean) map {

      case Success(image) => Ok(image).as(Barcodes.mimeType)
      case Failure(e) =>
        BadRequest("Couldn't generate bar code. Error: " + e.getMessage)
    }
  }
}
