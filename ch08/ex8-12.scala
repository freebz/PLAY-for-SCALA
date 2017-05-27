// 목록 8.12  JsPath를 사용하여 Writes[Product] 구현

import play.api.libs.json._
import play.api.libs.functional.syntax._
implicit val productWrites: Writes[Product] = (
  (JsPath \ "ean").write[Long] and
  (JsPath \ "name").write[String] and
  (JsPath \ "description").write[String]
)(unlift(Product.unapply))
