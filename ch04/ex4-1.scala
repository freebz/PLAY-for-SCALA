// 목록 4.1  컨트롤러 클래스와 4개의 액션 메소드

package controllers
import play.api.mvc.{Action, Controller}

object Products extends Controller {

  def list(pageNumber: Int) = Action {
    NotImplemented
  }

  def details(ean: Long) = Action {
    NotImplemented
  }

  def edit(ean: Long) = Action {
    NotImplemented
  }

  def update(ean: Long) = Action {
    NotImplemented
  }
}
