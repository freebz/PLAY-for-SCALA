// 목록 3.13  HTTP 요청 미루기 - app/controllers/Dashboard.scala

package controllers

import play.api.mvc.{Action, Controller}
import concurrent.{ExecutionContext, Future}

object Dashboard extends Controller {

  def backlog(warehouse: String) = Action {

    import ExecutionContext.Implicits.global
    val backlog = scala.concurrent.future {
      models.Order.backlog(warehouse)
    }
    Async {
      backlog.map(value => Ok(value))
    }
  }
}
