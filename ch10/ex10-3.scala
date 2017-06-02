// 목록 10.3  완성된 트위터 API 액션 메소드

def tweetList() = Action.async {
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
