// 목록 8.10  JSON 형식으로 상품 상세사항 출력 - app/controllers/Products.scala

def details(ean: Long) = Action {
  Product.findByEan(ean).map { product =>
    Ok(Json.toJson(product))
  }.getOrElse(NotFound)
}
