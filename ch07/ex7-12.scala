// 목록 7.12  다양한 필드를 검증하는 폼

val productForm = Form(mapping(
  "ean" -> longNumber.verifying("This product already exists!",
    Product.findByEan(_).isEmpty),
  "name" -> nonEmptyText,
  "description" -> text,
  "pieces" -> number,
  "active" -> boolean)(Product.apply)(Product.unapply).verifying(
  "Product can not be active if the description is empty",
    product =>
      !product.active || product.description.nonEmpty))
