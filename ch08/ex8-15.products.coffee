# 목록 8.15  각 열에 상품의 상세사항을 추가한 클라이언트 - products.coffee

jQuery ($) ->

  $table = $('.container table')
  productListUrl = $table.data('list')

  loadProductTable = ->
    $.get productListUrl, (products) ->
      $.each products, (index, eanCode) ->
        row  = $('<tr/>').append $('<td/>').text(eanCode)
        $table.append row
        loadProductDetails row

  productDetailsUrl = (eanCode) ->
    $table.data('details').replace '0', eanCode

  loadProductDetails = (tableRow) ->
    eanCode = tableRow.text()
    $.get productDetailsUrl(eanCode), (product) ->
      tableRow.append $('<td/>').text(product.name)
      tableRow.append $('<td/>').text(product.description)

  loadProductTable()
