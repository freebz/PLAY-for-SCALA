// 목록 10.1  tweetList 액션

def tweetList() = Action {
  val results = 3
  val query = """paperclip OR "paper clip""""
  val responseFuture =
    WS.url("http://search.twitter.com/search.json")
      .withQueryString("q" -> query, "rpp" -> results.toString)
      .get

  val response = Await.result(responseFuture, 10 seconds)
  val tweets = (Json.parse(response.body) \ "results").as[Seq[Tweet]]
  Ok(views.html.twitterrest.tweetlist(tweets))
}
