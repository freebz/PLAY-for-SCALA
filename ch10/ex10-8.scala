// 목록 10.8  3초마다 평균 부하를 보내는 웹 소켓 액션

def statusFeed() = WebSocket.using[String] { implicit request =>
  val in = Iteratee.ignore[String]
  val out = Enumerator.repeatM {
    Promise.timeout(getLoadAverage, 3 seconds
  }
  (in, out)
}
