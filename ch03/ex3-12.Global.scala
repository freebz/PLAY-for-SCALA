// 목록 3.12  담기 목록 생성 액터 - app/Global.scala

import java.util.Date
import models.PickList
class PickListActor(warehouse: String) extends Actor {
  def receive = { Handle messages case "send" => {
    val pickList = PickList.find(warehouse)
    Constructor for warehouse
    Render and send pick list
    val html = views.html.pickList(warehouse, pickList, new Date)
    send(html) }
    case _ => play.api.Logger.warn("unsupported message type")
  }
  def send(html: Html) {
    // ...
  } }
