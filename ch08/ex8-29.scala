// 목록 8.29  인증하기 전에 자격증명을 추출하도록 갱신된 액션 헬퍼

def AuthenticatedAction(f: Request[AnyContent] => Result):
    Action[AnyContent] = {
  Action {
    request =>
    val maybeCredentials = readQueryString(request)
    maybeCredentials.map { resultOrCredentials =>
      resultOrCredentials match {
        case Left(errorResult) => errorResult
        case Right(credentials) => {
          val (user, password) = credentials
          if (authenticate(user, password)) {
            f(request)
          } else {
            Unauthorized("Invalid user name or password")
          }
        }
      }
    }.getOrElse {
      Unauthorized("No user name and password provided")
    }
  }
}
