// 목록 7.15  LocalDate 포맷터

implicit val localDateFormatter = new Formatter[LocalDate] {
  def bind(key: String, data: Map[String, String]) =
    data.get(key) map { value =>
      Try {
        Right(LocalDate.parse(value))
      } getOrElse Left(Seq(FormError(key, "error.date", Nil)))
    } getOrElse Left(Seq(FormError(key, "error.required", Nil)))

  def unbind(key: String, ld: LocalDate) = Map(key -> ld.toString)

  override val format = Some(("date.format", Nil))
}
