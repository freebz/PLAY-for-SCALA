// 목록 7.17  파일 업로드 검증을 위해 ignored 맵핑과 커스텀 검증 사용하기

def upload() = Action(parse.multipartFormData) { implicit request =>
  val form = Form(tuple(
    "description" -> text,
    "image" -> ignored(request.body.file("image")).
      verifying("File missing", _.isDefined)))

  form.bindFromRequest.fold(
    formWithErrors => {
      Ok(views.html.fileupload.uploadform(formWithErrors))
    },
    value => Ok)
}
