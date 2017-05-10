// 목록 5.6  재고 아이템과 상품정보

def getAllProductsWithStockItems: Map[Product, List[StockItem]] = {
  DB.withConnection { implicit connection =>
    val sql = SQL("select p.*, s.* " +
      "from products p " +
      "inner join stock_items s on (p.id = s.product_id)")
    val results: List[(Product, StockItem)] =
      sql.as(productStockItemParser *)
    results.groupBy { _._1 }.mapValues { _.map { _._2 } }
  }
}
