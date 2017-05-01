// 목록 3.3  하위 환경설정 접근

current.configuration.getConfig("db.default").map { // Option[Configuration]
                                                    // 객체 반환
  databaseConfiguration =>
  databaseConfiguration.getString("driver").map(Logger.info(_))
  databaseConfiguration.getString("url").map(Logger.info(_))
}
