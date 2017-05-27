# 목록 8.8  서버로부터 데이터를 로드하기 위한 클라이언트 애플리케이션 - products.coffee

jQuery ($) ->

  $table = $('.container table')
  productListUrl = $table.data('list')

  $.get productListUrl, (products) ->
    $.each products, (index, eanCode) ->
      row = $('<tr/>').append $('<td/>').text(eanCode)
      $table.append row
