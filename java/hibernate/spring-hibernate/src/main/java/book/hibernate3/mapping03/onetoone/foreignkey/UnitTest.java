package book.hibernate3.mapping03.onetoone.foreignkey;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {
 
	@Test
	public void testMapping() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addResource("book/hibernate3/mapping02/onetoone/foreignkey/Member.hbm.xml")
				.addResource("book/hibernate3/mapping02/onetoone/foreignkey/MemberDetail.hbm.xml")
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------------------------------------

		Member member = new Member("username");
		MemberDetail memberDetail = new MemberDetail("zipcode", "address");
		
		// 관계설정
		member.setMemberDetail(memberDetail);
		memberDetail.setMember(member);
		
		// 객체저장
		session.save(member);
		// 객체로딩
		Member m = (Member) session.get(Member.class, new Integer(2));
		MemberDetail d = m.getMemberDetail();
		assertThat(m.getUsername(), is("username"));
		assertThat(d.getZipcode(), is("zipcode"));
		assertThat(d.getAddress(), is("address"));
		
		// 객체삭제
		session.delete(m);

		// -------------------------------------------------------------------------------
		tx.commit();
		session.close();
	}
	
}
