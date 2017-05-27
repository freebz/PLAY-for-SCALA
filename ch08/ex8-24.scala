// 목록 8.24  Reads[Company]와 Reads[Product]의 유효성 검증 규칙

implicit val companyReads: Reads[Company] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "contact_details").read(
  (
    (JsPath \ "email").readNullable[String](email) and
    (JsPath \ "fax").readNullable[String](minLength[String](10)) and
    (JsPath \ "phone").readNullable[String](minLength[String](10))
  )(Contact.apply _))
  )(Company.apply _)

implicit val productReads: Reads[Product] = (
  (JsPath \ "ean").read[Long] and
  (JsPath \ "name").read[String](minLength[String](5)) and
  (JsPath \ "description").readNullable[String] and
  (JsPath \ "pieces").readNullable[Int] and
  (JsPath \ "manufacturer").read[Company] and
  (JsPath \ "tags").read[List[String]] and
  (JsPath \ "active").read[Boolean]
)(Product.apply _)
