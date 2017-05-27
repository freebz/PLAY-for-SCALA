// 목록 8.25  상품 상세사항을 저장하고 유효성을 검증하는 컨트롤러 액션 - Products.scala

def save = Action(parse.json) { implicit request =>
  val json = request.body
  json.validate[Product].fold(
    valid = { product =>
      Product.save(product)
      Ok("Saved")
    },
    invalid = {
      errors => BadRequest(JsError.toFlatJson(errors))
    }
  )
}
