// 목록 8.27  인증을 수행하는 액션 헬퍼

def authenticate(request: Request[AnyContent]) = true

def AuthenticatedAction(f: Request[AnyContent] => Result):
    Action[AnyContent] = {
  Action { request =>
    if (authenticate(request)) {
      f(request)
    } else {
      Unauthorized
    }
  }
}
