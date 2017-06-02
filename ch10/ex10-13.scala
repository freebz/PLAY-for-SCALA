// 목록 10.13  아마존 S3 업로드 바디 파서, buildRequest 메소드

def buildRequest(bucket: String, objectId: String, key: String,
  secret: String, requestHeader: RequestHeader):
    (Request, FeedableBodyGenerator) = {

  val expires = dateFormat.format(new Date())
  val path = "/%s/%s" format (bucket, objectId)
  val acl = "public-read"
  val contentType = requestHeader.headers.get(HeaderNames.CONTENT_TYPE)
    .getOrElse("binary/octet-stream")
  val auth = "AWS %s:%s" format (key, sign("PUT", path, secret,
    expires, Some(contentType), Some(acl)))

  val url = "https://%s.s3.amazonaws.com/%s" format (bucket, objectId)

  val bodyGenerator = new FeedableBodyGenerator()

  val request = new RequestBuilder("PUT")
    .setUrl(url)
    .setHeader("Date", expires)
    .setHeader("x-amz-acl", acl)
    .setHeader("Content-Type", contentType)
    .setHeader("Authorization", auth)
    .setContentLength(requestHeader.headers
      .get(HeaderNames.CONTENT_LENGHT).get.toInt)
    .setBody(bodyGenerator)
    .build()
  (request, bodyGenerator)
}
