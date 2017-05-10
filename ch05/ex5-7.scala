// 목록 5.7  레코드 삽입

def insert(product: Product): Boolean =
  DB.withConnection { implicit connection =>
    val addedRows = SQL("""insert
    into products
    values ({id}, {ean}, {name}, {description})""").on(
      "id" -> product.id,
      "ean" -> product.ean,
      "name" -> product.name,
      "description" -> product.description
    ).executeUpdate()
    addedRows == 1
  }
