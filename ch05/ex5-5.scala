// 목록 5.5  Product 파싱하기

import anorm.RowParser

val productParser: RowParser[Product] = {
  import anorm.~
  import anorm.SqlParser._

  long("id") ~
  long("ean") ~
  str("name") ~
  str("description") map {
    case id ~ ean ~ name ~ description =>
      Product(id, ean, name, description)
  }
}
