// 목록 8.6  JSON 라이브러리 케이스 클래스로 생성된 중첩된 JSON 구조

val product = Json.obj(
  "name" -> JsString("Blue Paper clips"),
  "ean" -> JsString("12345432123"),
  "description" -> JsString("Big box of paper clips"),
  "pieces" -> JsNumber(500),
  "manufacturer" -> Json.obj(
    "name" -> JsString("Paperclipfactory Inc."),
    "contact_details" -> Json.obj(
      "email" -> JsString("contact@paperclipfactory.example.com"),
      "fax" -> JsNull,
      "phone" -> JsString("+12345654321")
    )
  ),
  "tags" -> Json.arr(
    JsString("paperclip"),
    JsString("coated")
  ),
  "active -> JsBoolean(true)
)
