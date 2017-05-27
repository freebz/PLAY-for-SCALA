// 목록 8.13  purchase_price 노출하는 다른 Writes[Product]

import play.api.libs.json._
import play.api.libs.functional.syntax._

val adminProductWrites: Writes[Product] = (
  (JsPath \ "ean").write[Long] and
  (JsPath \ "name").write[String] and
  (JsPath \ "description").write[String] and
  (JsPath \ "price").write[BigDecimal]
)(unlift(Product.unapply))
