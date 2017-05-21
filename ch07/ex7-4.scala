// 목록 7.4  요청으로부터 폼을 바인딩하는 create액션 메소드

def create() = Action { implicit request =>
  productForm.bindFromRequest.fold(
    formWithErrors => BadRequest("Oh noes, invalid submission!"),
    value => Ok("created: " + value)
  )
}
