// 목록 7.2  목록 7.1의 HTML 폼 요청을 검증하는 플레이 2 폼

val userForm = Form(
  mapping(
    "username" -> nonEmptyText(8),
    "realname" -> optional(text),
    "email" -> email)(User.apply)(User.unapply))

def createUser() = Action { implicit request =>
  userForm.bindFromRequest.fold(
    fromWithErrors => BadRequest,
    user => Ok("User OK!"))
}
