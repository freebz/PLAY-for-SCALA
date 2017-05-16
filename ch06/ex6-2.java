// 목록 6.2  플레이 1.x 자바 컨트롤러 예제

public class Articles extends Controller {
  public static void index() {
    articles = Article.findAll();
    render(articles);
  }
  public static void show(Long id) {
    article = Article.find("byId", id).first();
    render(article);
  }
}
