// 목록 3.10  담기 목록 컨트롤러 - app/controllers/PickLists.scala

import java.util.Date
import models.PickList
import scala.concurrent.{ExecutionContext, future}

def sendAsync(warehouse: String) = Action {
  import ExecutionContext.Implicits.global

  future {
    val pickList = PickList.find(warehouse)
    send(views.html.pickList(warehouse, pickList, new Date))
  }

  Redirect(routes.PickLists.index())
}
