// 목록 5.3  질의 결과를 엔티티로 변환하기

import play.api.Play.current
import play.api.db.DB
def getAll: List[Product] = DB.withConnection {
  implicit connection =>
  sql().fold ( row =>
    Product(row[Long]("id"), row[Long]("ean"),
      row[String]("name"), row[String]("description"))
  ).toList
}
