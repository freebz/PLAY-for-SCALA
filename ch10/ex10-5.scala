// 목록 10.5  OAuth를 이용한 요청 서명

val consumerKey = ConsumerKey(
  "52xEY4sBgPl01FCQRaiAg",
  "KpnmEeDM6XDwS59FDcAmVMQbui8mcceNASj7xFJc5WY")

val accessToken = RequestToken(
  "16905598-cIPuAsWUI47Fk78guCRTa7QX49G0nOQdwv2SA6Rjz",
  "yEKoKqqOjo4gtSQ6FSsQ9tbxQqQZNq7LB5NGsbyKU")

def postTweet() = Action {

  val message = "Hi! This is an automated tweet!"
  val data = Map("status" -> Seq(message))

  val responseFuture =
    WS.url("http://api.twitter.com/1/statuses/update.json")
      .sign(OAuthCalculator(consumerKey, accessToken)).post(data)

  Async(responseFuture.map(response => Ok(response.body)))
}
