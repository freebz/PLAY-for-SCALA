// 목록 3.2  현재 애플리케이션의 환경설정을 읽기 위해 플레이 API사용

import play.api.Play.current  // 환경설정에 접근하기 위해 애플리케이션 인스턴스 임포트
current.configuration.getString("db.default.url").map {
  databaseUrl => Logger.info(databaseUrl)  // 데이터베이스URL에서 Option값으로
                                           // getString 반환
}


current.configuration.getBoolean("db.default.logStatements").foreach {
  if (_) Logger.info("Logging SQL statements...")
}
