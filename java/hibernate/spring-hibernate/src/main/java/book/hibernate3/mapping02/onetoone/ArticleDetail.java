package book.hibernate3.mapping02.onetoone;



public class ArticleDetail {

	private int id;
	private Article article;
	private String content;
	
	public ArticleDetail() {}
	public ArticleDetail(String content) {
		this.content = content;
	}
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public Article getArticle() {
    return article;
  }
  public void setArticle(Article article) {
    this.article = article;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
	
	
}
