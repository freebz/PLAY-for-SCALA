// 목록 10.4  전체 액션 캐싱하기

def tweetList() = Cached("action-tweets") {
  Action.async {
    val results = 3
    val query = """paperclip OR "paper clip""""
    val responseFuture =
      WS.url("http://search.twitter.com/search.json")
        .withQueryString("q" -> query, "rpp" -> results.toString)
        .get

    responseFuture.map { response =>
      val tweets = Json.parse(response.body).\("results").as[Seq[Tweet]]
      Ok(views.html.twitterrest.tweetlist(tweets))
    }
  }
}
