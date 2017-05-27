// 목록 8.19  Reads[Product] 구현체

import play.api.libs.json._
import play.api.libs.functional.syntax._
implicit val productReads: Reads[PricedProduct] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "description").readNullable[String] and
  (JsPath \ "purchase_price").read[BigDecimal] and
  (JsPath \ "selling_price").read[BigDecimal]
)(PricedProduct.apply _)
