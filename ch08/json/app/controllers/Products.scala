package controllers

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.libs.json.Json


class Products extends Controller {

  def list = Action {
    val productCodes = Product.findAll.map(_.ean)

    Ok(Json.toJson(productCodes))
  }

  /*
  import play.api.libs.json._
  implicit object ProductWrites extends Writes[Product] {
    def writes(p: Product) = Json.obj(
      "ean" -> Json.toJson(p.ean),
      "name" -> Json.toJson(p.name),
      "description" -> Json.toJson(p.description)
    )
  }
   */

  def details(ean: Long) = Action {
    Product.findByEan(ean).map { product =>
      Ok(Json.toJson(product))
    }.getOrElse(NotFound)
  }

  import play.api.libs.json._
  import play.api.libs.functional.syntax._
  /*
  implicit val productWrites: Writes[Product] = (
    (JsPath \ "ean").write[Long] and
    (JsPath \ "name").write[String] and
    (JsPath \ "description").write[String]
  )(unlift(Product.unapply))
   */

  implicit val productWrites = Json.writes[Product]

  /*
  val adminProductWrites: Writes[Product] = (
    (JsPath \ "ean").write[Long] and
    (JsPath \ "name").write[String] and
    (JsPath \ "description").write[String] and
    (JsPath \ "price").write[BigDecimal]
  )(unlift(Product.unapply))
   */

  /*
  def save(ean: Long) = Action(parse.json) { request =>
    val productJson = request.body
    val product = productJson.as[Product]
    try {
      Product.save(product)
      Ok("Saved")
    }
    catch {
      case e:IllegalArgumentException =>
        BadRequest("Product not found")
    }
  }
   */

  import play.api.libs.functional.syntax._

  implicit val productReads: Reads[Product] = (
    (JsPath \ "ean").read[Long] and
    (JsPath \ "name").read[String] and
    (JsPath \ "description").read[String]
  )(Product.apply _)

  def save(ean: Long) = Action(parse.json) { implicit request =>
    val json = request.body
    json.validate[Product].fold(
      valid = { product =>
        Product.save(product)
        Ok("Saved")
      },
      invalid = {
        errors => BadRequest(JsError.toFlatJson(errors))
      }
    )
  }
}
