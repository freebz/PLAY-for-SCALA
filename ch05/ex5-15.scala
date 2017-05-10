// 목록 5.15  상태가 없는 관계 버전으로 모델 재정의

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.dsl.{OneToMany, ManyToOne}
import org.squeryl.{Query, Schema, KeyedEntity, Table}

object Database extends Schema {
  val productsTable = table[Product]("products")
  val warehousesTable = table[Warehouse]("warehouses")
  val stockItemsTable = table[StockItem]("stock_items")

  val productToStockItems =
    oneToManyRelation(productsTable, stockItemsTable).
      via((p,s) => p.id === s.productId)

  val warehouseToStockItems =
    oneToManyRelation(warehousesTable, stockItemsTable).
      via((w,s) => w.id === s.warehouseId)
}

case class Product(
  id: Long,
  ean: Long,
  name: String,
  description: String) extends KeyedEntity[Long] {
  lazy val stockItems: OneToMany[StockItem] =
    Database.productToStockItems.left(this)
}

case class Warehouse(
  id: Long,
  name: String) extends KeyedEntity[Long] {
  lazy val stockItems: OneToMany[StockItem] =
    Database.warehouseToStockItems.left(this)
}

case class StockItem(
  id: Long,
  productId: Long,
  warehouseId: Long,
  quantity: Long) extends KeyedEntity[Long] {
  lazy val product: ManyToOne[Product] =
    Database.productToStockItems.right(this)

  lazy val warehouse: ManyToOne[Warehouse] =
    Database.warehouseToStockItems.right(this)
}
