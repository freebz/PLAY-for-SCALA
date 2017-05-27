// 목록 8.11  Writes[Product] 구현체

import play.api.libs.json._
implicit object ProductWrite extends Writes[Product] {
  def writes(p: Product) = Json.obj(
    "ean" -> Json.toJson(p.ean),
    "name" -> Json.toJson(p.name),
    "description" -> Json.toJson(p.description)
  )
}
