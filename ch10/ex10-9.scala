// 목록 10.9  컨트롤러에 정의된 Mutable 상태: 안전하지 않음

object Chat extends Controller {
  var users = Set[String]()

  def webSocket(username: String) { request => users += username
    // ... // Create iteratee etc.
  }
}
