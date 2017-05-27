// 목록 8.20  Format[Product] 구현체

import play.api.libs.json._
import play.api.libs.functional.syntax._
implicit val productFormat = (
  (JsPath \ "name").format[String] and
  (JsPath \ "description").formatNullable[String] and
  (JsPath \ "purchase_price").format[BigDecimal] and
  (JsPath \ "selling_price").format[BigDecimal]
)(PricedProduct.apply, unlift(PricedProduct.unapply))
