// 목록 5.9  Squeryl 초기화 하기

import org.squeryl.adapters.H2Adapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.DB
import play.api.{Application, GlobalSettings}

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    SessionFactory.concreteFatcory = Some(() =>
      Session.create(DB.getConnection()(app), new H2Adapter) )
  } }
