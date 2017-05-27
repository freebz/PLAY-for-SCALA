# 목록 8.16  서버가 테이블을 수정하고 갱신할 수 있도록 커피스크립트 수정 - products.coffee

jQuery ($) ->

  $table = $('.container table')
  productListUrl = $table.data('list')

  loadProductTable = ->
    $.get productListUrl, (products) ->
      $.each products, (index, eanCode) ->
        row  = $('<tr/>').append $('<td/>').text(eanCode)
        row.attr 'contenteditable', true
        $table.append row
        loadProductDetails row

  productDetailsUrl = (eanCode) ->
    $table.data('details').replace '0', eanCode

  loadProductDetails = (tableRow) ->
    eanCode = tableRow.text()
    $.get productDetailsUrl(eanCode), (product) ->
      tableRow.append $('<td/>').text(product.name)
      tableRow.append $('<td/>').text(product.description)
      tableRow.append $('<td/>')

  loadProductTable()

  saveRow = ($row) ->
    [ean, name, description] = $row.children().map -> $(this).text()
    product =
      ean: parseInt(ean)
      name: name
      description: description
    jqxhr = $.ajax
      type: "PUT"
      url: productDetailsUrl(ean)
      contentType: "application/json"
      data: JSON.stringify product
    jqxhr.done (response) ->
      $label = $('<span/>').addClass('label label-success')
      $row.children().last().append $label.text(response)
      $label.delay(3000).fadeOut()
    jqxhr.fail (data) ->
      $label = $('<span/>').addClass('label label-danger')
      message = data.responseText || data.statusText
      $row.children().last().append $label.text(message)

  $table.on 'focusout', 'tr', () ->
    console.log('save')
    saveRow $(this)

