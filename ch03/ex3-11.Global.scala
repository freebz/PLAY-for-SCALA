// 목록 3.11  전역 설정 오브젝트 - app/Global.scala

import akka.actor.{Actor, Props}
import models.Warehouse
import play.api.libs.concurrent.Akka
import play.api.GlobalSettings
import play.api.templates.Html
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object Global extends GlobalSettings {
  override def onStart(application: play.api.Application) {
    import scala.concurrent.duration._
    import play.api.Play.current
    for (warehouse <- Warehouse.find()) {
      val actor = Akka.system.actorOf(
        Props(new PickListActor(warehouse)))
      Akka.system.scheduler.schedule( 0.seconds, 30.minutes, actor, "send")
    }
  }
}
