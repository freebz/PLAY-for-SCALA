// 목록 10.6  간단한 logging 이터레이티로 트위터의 스트리밍 API 사용하기

import play.api._
import play.api.mvc._
import play.api.Play._
import play.api.libs.oauth.{ ConsumerKey, OAuthCalculator, RequestToken }
import play.api.libs.iteratee.Iteratee
import play.api.libs.ws.WS
import scala.concurrent.ExecutionContext.Implicits.global

object Global extends GlobalSettings {

  val consumerKey = ConsumerKey("GvuvcUUGoeIYy0Zs2vpcRFACW",
    "dBGs9zzIZY4hDERPNS4UjxBpP0RYc5qg76hSP1oADCZBhsLkkE")

  val accessToken = RequestToken(
    "147837726-jpQlMb0UBkGiI54XAQpVS40LdAnfkM9TeaxAk7ZS",
    "x9yCUchCA8FEBKC6pZXuUHr3qi4rendZJPngZmzSkTv57")

  val loggingIteratee = Iteratee.foreach[Array[Byte]] { chunk =>
    val chunkString = new String(chunk, "UTF-8")
    println(chunkString)
  }

  override def onStart(app: Application) {
    WS.url("https://stream.twitter.com/1/statuses/sample.json")
      .sign(OAuthCalculator(consumerKey, accessToken))
      .get(_ => loggingIteratee)
  }
}
