// 목록 5.14  트랜잭션 사용하기

import models.{ Database, Product, StockItem }
import org.squeryl.PrimitiveTypeMode.transaction
import Database.{productsTable, stockItemsTable}

def addNewProductGood(product: Product, stockItem: StockItem) {
  transaction {
    productsTable.insert(product)
    stockItemsTable.insert(stockItem)
  }
}

def addNewProductBad(product: Product, stockItem: StockItem) {
  transaction {
    productsTable.insert(product)
  }
  transaction {
    stockItemsTable.insert(stockItem)
  }
}
