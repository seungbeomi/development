package org.javabrains.koushks33.configuring_second_level_cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.javabrains.koushks30.understanding_restrictions.UserDetails;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		Configuration cfg = new Configuration();
		cfg.configure("hbm2ddl.xml");
		cfg.addAnnotatedClass(UserDetails.class);

		/* Second level cache
	    	<property name="cache.use_second_level_cache">true</property>
	    	<property name="cache.provider_class">org.hibernate.cache.EhCacheProvider</property>
		 */
		
		SessionFactory factory = cfg.buildSessionFactory();
		{
			Session session = factory.openSession();
			session.beginTransaction();
	
			UserDetails user = new UserDetails();
			user.setUserName("user");
			session.save(user);
			
	
			UserDetails user1 = (UserDetails) session.get(UserDetails.class, 1);
	
			session.getTransaction().commit();
			session.close();
		}
		{
			Session session = factory.openSession();
			session.beginTransaction();
	
			UserDetails user2 = (UserDetails) session.get(UserDetails.class, 1);
	
			session.getTransaction().commit();
			session.close();
		}

	}

}
