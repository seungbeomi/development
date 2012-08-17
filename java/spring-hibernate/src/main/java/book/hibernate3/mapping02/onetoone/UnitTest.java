package book.hibernate3.mapping02.onetoone;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {
	@Test
	public void testMapping () {
		SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addResource("book/hibernate3/mapping02/onetoone/Article.hbm.xml")
			.addResource("book/hibernate3/mapping02/onetoone/ArticleDetail.hbm.xml")
			.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------------------------------------

		Article article = new Article("title");
		ArticleDetail detail = new ArticleDetail("content");
		
		// article과 detail 연결
		article.setDetail(detail);
		detail.setArticle(article);
		
		session.save(article);

		// 객체 로딩
		Article a = (Article) session.get(Article.class, new Integer(37)); 
		ArticleDetail d = a.getDetail();
		assertThat(d.getContent(), is("content"));
		
		// 객체 삭제
		session.delete(a);	// cascade속성의 delete값에 따라 detail도 삭제된다

		// -------------------------------------------------------------------------------
		tx.commit();
		session.close();
	}
	
}
