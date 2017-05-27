// 목록 8.17  상품의 상세사항을 저장하는 컨트롤러 액션 - Products.scala

def save(ean: Long) = Action(parse.json) { request =>
  val productJson = request.body
  val product = productJson.as[Product]
  try {
    Product.save(product)
    Ok("Saved")
  }
  catch {
    case e:IllegalArgumentException =>
      BadRequest("Product not found")
  }
}
