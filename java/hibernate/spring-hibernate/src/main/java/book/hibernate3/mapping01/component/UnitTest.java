package book.hibernate3.mapping01.component;


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
			.addResource("book/hibernate3/mapping01/component/Employment.hbm.xml")
			.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------------------------------------
		
		Employment emp = new Employment();
		Name korName = new Name("성", "이름");
		FamilyInfo f1 = new FamilyInfo("오리진");
		korName.setFamilyInfo(f1);
		Name engName = new Name("surname", "name");
		emp.setKorName(korName);
		emp.setEngName(engName);
		
		// 저장
		session.save(emp);
		
		tx.commit(); // commit시에 세션의 내용 저장

		// 조회
		Employment e = (Employment) session.get(Employment.class, new Integer(1));
		Name kName = e.getKorName();
		assertThat(kName.getSurname(), is("성"));
		Name eName = e.getEngName();
		assertThat(eName.getSurname(), is("surname"));
		
		// 컴포넌트에서 부모 객체 참조하기
		Employment p = kName.getEmployment();
		assertThat(p.getEngName().getName(), is("name"));
		
		// 컴포넌트 중첩
		FamilyInfo f = kName.getFamilyInfo();
		assertThat(f.getOrigin(), is("오리진"));
		
		// -------------------------------------------------------------------------------
		session.close();
	}
	
}
