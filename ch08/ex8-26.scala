// 목록 8.26  JSON 응답을 위한 JSON 유효성 검증 오류 포맷팅하기

implicit val JsPathWrites =
  Writes[JsPath](p => JsString(p.toString))

implicit val ValidationErrorWrites =
  Writes[ValidationError](e => JsString(e.message))

implicit val jsonValidateErrorWrites =
  ( (JsPath \ "path").write[JsPath] and
  (JsPath \ "errors").write[Seq[ValidationError]]
  tupled
)
