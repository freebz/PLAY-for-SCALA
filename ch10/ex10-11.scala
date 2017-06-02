// 목록 10.11  채팅 컨트롤러

object Chat extends Controller {

  implicit val timeout = Timeout(1 seconds)
  val room = Akka.system.actorOf(Props[ChatRoom])

  def showRoom(nick: String) = Action { implicit request =>
    Ok(views.html.chat.showRoom(nick))
  }

  def shatSocket(nick: String) = WebSocket.tryAccept[String] { request =>
    val channelsFuture = room ? Join(nick)
    channelsFuture.mapTo[(Iteratee[String, _], Enumerator[String])].
      map { res =>
        Right(res)
      }
  }
}
