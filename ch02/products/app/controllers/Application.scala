package controllers

import play.api.mvc.{Action, Controller}

class Application extends Controller {
  def index = Action {
    Redirect(routes.Products.list())    // 상품 목록으로 리다이렉트
  }
}
