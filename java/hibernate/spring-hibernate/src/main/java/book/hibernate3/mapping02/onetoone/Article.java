package book.hibernate3.mapping02.onetoone;

import lombok.Data;


@Data
public class Article {

	private int id;
	private String title;
	private ArticleDetail detail;
	
	public Article() {}
	public Article(String title) {
		this.title = title;
	}
	
}
