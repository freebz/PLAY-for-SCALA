// 목록 7.10  implicit FieldConstructor를 가지는 bootstrap 패키지 객체

package views.html.helper

object twitterBootstrap {
  implicit val twitterBootstrapField = FieldConstructor(bootstrap.bootstrapFieldConstructor.f)
}
