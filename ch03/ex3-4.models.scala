// 목록 3.4  도메인 모델 클래스 - app/models/models.scala

case class Product {
  id: Long,
  ean: Long,
  name: String,
  description: String
)

case class Warehouse(id: Long, name: String)

case class StockItem(
  id: Long,
  productId: Long,
  warehouseId: Long,
  quantity: Long
)



// 3.3.5 Slick을 사용해서 데이터베이스 접근하기

object Product extends Table[(Long, String, String)]("products") {
  def ean = column[Long]("ean", O.PrimaryKey)
  def name = column[String]("name")
  def description = column[String]("description")
  def * = ean ~ name ~ description
}


val products = for {
  product <- Product.sortBy(product => product.name.asc)
} yeild (product)


val url = "jdbc:postgresql://localhost/slick?user=slick&password=slick"
Database.forURL(url, driver = "org.postgresql.Driver") withSession {
  val productList = products.list
}
