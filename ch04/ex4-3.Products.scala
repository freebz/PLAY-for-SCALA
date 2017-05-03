// 목록 4.3  바 코드 컨트롤러 액션 - app/controllers/Products.scala

def barcode(ean: Long) = Action {
  import java.lang.IllegalArgumentException
  val MimeType = "image/png"
  try {
    val imageData: Array[Byte] =
      ean13BarCode(ean, MimeType)
    Ok(imageData).as(MimeType)

  } catch {
    case e: IllegalArgumentException =>
      BadRequest("Could not generate bar code. Error: " + e.getMessage)
  }
}
