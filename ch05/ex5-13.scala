// 목록 5.13  중첩된 쿼리

def productsInWarehouse(warehouse: Warehouse) = {
  join(productsTable, stockItemsTable)((product, stockItem) =>
    where(stockItem.location === warehouse.id).
      select(product).
      on(stockItem.product === product.id)
  )
}

def productsInWarehouseByName(name: String,
  warehouse: Warehouse): Query[Product] = {
  from(productsInWarehouse(warehouse)){ product =>
    where(product.name like name).select(product)
  }
}
