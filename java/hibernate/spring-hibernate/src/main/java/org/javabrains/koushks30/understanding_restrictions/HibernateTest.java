package org.javabrains.koushks30.understanding_restrictions;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.configure("hbm2ddl.xml");
		cfg.addAnnotatedClass(UserDetails.class);

		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		for (int i=1; i <= 10; i++) {
			UserDetails user = new UserDetails();
			user.setUserName("user"+ i);
			session.save(user);
		}
		
		Criteria criteria = session.createCriteria(UserDetails.class);
		/*
		criteria.add(Restrictions.like("userName", "%user%"))
				.add(Restrictions.gt("userId", 5))
				.add(Restrictions.between("userId", 7, 9));
		*/
		criteria.add(
				Restrictions.or(
						Restrictions.between("userId", 0, 3), 
						Restrictions.between("userId", 7, 10)));
		List<UserDetails> list = criteria.list();
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails u : list) {
			System.out.println(u);
		}
		
	}

}
