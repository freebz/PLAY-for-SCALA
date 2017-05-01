// 목록 3.9  담기 목록 컨트롤러 - app/controllers/PickLists.scala

object PickLists extends Controller {

  def preview(warehouse: String) = Action {
    val pickList = PickList.find(warehouse)
    val timestamp = new java.util.Date
    Ok(views.html.pickList(warehouse, pickList, timestamp))
  }
}
