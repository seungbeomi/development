package book.hibernate3.mapping02.onetoone;

import lombok.Data;


@Data
public class ArticleDetail {

	private int id;
	private Article article;
	private String content;
	
	public ArticleDetail() {}
	public ArticleDetail(String content) {
		this.content = content;
	}
	
}
