// 목록 8.30  기본 인증 헤더에서 자격증명을 추출하는 헬퍼 함수

def readBasicAuthentication(headers: Headers): Option[Either[Result,
  (String, String)]] = {

  headers.get(http.HeaderNames.AUTHORIZATION).map { header =>

    val BasicHeader = "Basic (.*)".r
    header match {
      case BasicHeader(base64) => {
        try {
          import org.apache.commons.codec.binary.Base64
          val decodedBytes =
            Base64.decodeBase64(base64.getBytes)
          val credentials =
            new String(decodedBytes).split(":", 2)
          if (credentials.length != 2) {
            Left(BadRequest("Invalid basic authentication"))
          } else {
            val (user, password) = (credentials(0), credentials(1))
            Right((user, password))
          }
        }
      }
      case _ => Left(BadRequest("Invalid Authorization header"))
    }
  }
}
