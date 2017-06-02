// 목록 10.14  아마존 S3 바디 파서

def S3Upload(bucket: String, objectId: String) = BodyParser {
  requestHeader =>
  val awsSecret = Play.configuration.getString("aws.secret").get
  val awsKey = Play.configuration.getString("aws.key").get
  val (request, bodyGenerator) =
    buildRequest(bucket, objectId, awsKey, awsSecret, requestHeader)
  S3Writer(objectId, request, bodyGenerator)
}

def S3Writer(objectId, request: Request,
  bodyGenerator: FeedableBodyGenerator):
    Iteratee[Array[Byte], Either[Result, String]] = {

  // We execute the request, but we can send body chunks afterwards.
  val responseFuture = client.executeRequest(request)

  Iteratee.fold[Array[Byte], FeedableBodyGenerator]
    (bodyGenerator) {
    (generator, bytes) =>
    val isLast = false
    generator.feed(new ByteBufferWrapper(ByteBuffer.wrap(bytes)),
      isLast)

    generator
  } mapDone { generator =>
    val isLast = true
    val emptyBuffer =
      new ByteBufferWrapper(ByteBuffer.wrap(Array[Byte]()))
    generator.feed(emptyBuffer, isLast)
    val response = responseFuture.get
    response.getStatusCode match {
      case 200 => Right(objectId)
      case _ => Left(Forbidden(response.getResponseBody))
    }
  }
}
