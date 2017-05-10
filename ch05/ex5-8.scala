// 목록 5.8  갱신과 삭제

def update(product: Product): Boolean =
  DB.withConnection { implicit connection =>
    val updatedRows = SQL("""update products
    set name = {name},
    ean = {ean},
    description = {description}
    where id = {id}
      """).on(
        "id" -> product.id,
        "name" -> product.name,
        "ean" -> product.ean,
        "description" -> product.description).
      executeUpdate()
    updatedRows == 1
  }

def delete(product: Product): Boolean =
  DB.withConnection { implicit connection =>
    val updatedRows = SQL("delete from products where id = {id}").
      on("id" -> product.id).executeUpdate()
    updatedRows == 0
  }
