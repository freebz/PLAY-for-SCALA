// 목록 8.18  Reads[Product] 구현

import play.api.libs.functional.syntax._

implicit val productReads: Reads[Product] = (
  (JsPath \ "ean").read[Long] and
  (JsPath \ "name").read[String] and
  (JsPath \ "description").read[String]
)(Product.apply _)
