// 목록 5.12  자연스러운 스키마

import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._

object Database extends Schema {

  val productsTable = table[Product]("products")
  val stockItemsTable = table[StockItem]("stock_items")
  val warehousesTable = table[Warehouse]("warehouses")

  on(productsTable) { p => declare {
    p.id is(autoIncremented)
  }}
  on(stockItemsTable) { s => declare {
    s.id is(autoIncremented)
  }}
  on(warehousesTable) { w => declare {
    w.id is(autoIncremented)
  }}
}
