// 목록 6.5  플레이2 스칼라 컨트롤러 예제

object Articles extends Controller {

  def index = Action {
    val articles = Article.findAll()
    Ok(views.html.articles.index(articles))
  }

  def show(id: Long) = Action {
    Article.findById(id) match {
      case None => NotFound
      case Some(article) => Ok(views.html.articles.show(article))
    }
  }
}
