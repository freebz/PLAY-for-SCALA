// 목록 8.28  요청 쿼리스트링에서 자격증명을 추출하는 헬퍼 함수

def readQueryString(request: Request[_]): Option[Either[Result,
  (String, String)]] = {
  request.queryString.get("user").map { user =>
    request.queryString.get("password").map { password =>
      Right((user.head, password.head))
    }.getOrElse {
      Left(BadRequest("Password not specified"))
    }
  }
}
