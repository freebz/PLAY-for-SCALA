// 목록 5.4  질의 결과를 변환하기 위해 패턴 매칭을 사용

def getAllWithPatterns: List[Product] = DB.withConnection {
  implicit connection =>
  import anorm.Row

  sql().collect {
    case Row(Some(id: Long), Some(ean: Long), Some(name: String),
      Some(description: String)) =>
      Product(id, ean, name, description)
  }.toList
}
