package book.hibernate3.mapping02.onetoone;


public class Article {

  private int id;
  private String title;
  private ArticleDetail detail;

  public Article() {
  }

  public Article(String title) {
    this.title = title;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArticleDetail getDetail() {
    return detail;
  }

  public void setDetail(ArticleDetail detail) {
    this.detail = detail;
  }

}
