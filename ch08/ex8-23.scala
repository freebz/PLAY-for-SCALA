// 목록 8.23  JSON 파서 정의

implicit val companyReads: Reads[Company] = (
  (JsPath \ "name").read[String] and
  (JsPath \ "contact_details").read(
    (
      (JsPath \ "email").readNullable[String] and
      (JsPath \ "fax").readNullable[String] and
      (JsPath \ "phone").readNullable[String]
    )(Contact.apply _))
  )(Company.apply _)

implicit val productReads: Reads[Product] = (
  (JsPath \ "ean").read[Long] and
  (JsPath \ "name").read[String] and
  (JsPath \ "description").readNullable[String] and
  (JsPath \ "pieces").readNullable[Int] and
  (JsPath \ "manufacturer").read[Company] and
  (JsPath \ "tags").read[List[String]] and
  (JsPath \ "active").read[Boolean]
)(Product.apply _)
