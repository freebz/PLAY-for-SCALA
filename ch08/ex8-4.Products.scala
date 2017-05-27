// 목록 8.4  JSON 배열을 반환하는 list 액션 - app/controllers/Products.scala

package controllers

import play.api.mvc.{Action, Controller}
import models.Product
import play.api.libs.json.Json

class Products extends Controller {

  def list = Action {
    val productCodes = Product.findAll.map(_.ean)

    Ok(Json.toJson(productCodes))
  }
}
