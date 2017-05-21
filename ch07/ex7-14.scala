// 목록 7.14  플레이 Formatter 트레이트 정의

trait Formatter[T] {
  def bind(key: String, data: Map[String, String]):
      Either[Seq[FormError], T]
  def unbind(key: String, value: T): Map[String, String]
  val format: Option[(String, Seq[Any])] = None
}
