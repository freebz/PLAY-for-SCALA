// 목록 6.13  implicit 카트 제공하기

def catalog() = Action { implicit request =>
  val products = ProductDAO.list
  Ok(views.html.shop.catalog(products))
}

implicit def cart(implicit request: RequestHeader) = {
  // Get cart from session
}
