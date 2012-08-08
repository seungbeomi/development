package com.packtpub.springhibernate.ch03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class StudentTest {

	@Test
	public void testConfigProperty() {
		// configuration Hibernate
		Configuration config = new Configuration();
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		config.setProperty("hibernate.connection.driver_class", "net.sf.log4jdbc.DriverSpy");
		config.setProperty("hibernate.connection.url", "jdbc:log4jdbc:h2:~/hiberdb");
		config.setProperty("hibernate.connection.username", "sa");
		config.setProperty("hibernate.connection.password", "");
		config.setProperty("hibernate.show_sql", "true");
		config.setProperty("hibernate.format_sql", "true");
		config.setProperty("hibernate.use_sql_comments", "true");
		
		// introducing persistent classes to Hibernate
		config.addClass(Student.class);
		
		// obtaining a session object
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		
		// starting a transaction
		Transaction tx = session.beginTransaction();
		
		// persisting...
		Student student = new Student("Andrew", "White");
		session.save(student);
		
		// session.connection().setAutoCommit(true);
		
		// commiting the transaction
		tx.commit(); // tx.rollback();
		session.close();
	}
	
	@Test
	public void testConfigFile() {
		Configuration config = new Configuration();
		config.configure();
		
		// obtaining a session object
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		
		// starting a transaction
		Transaction tx = session.beginTransaction();
		
		// persisting...
		Student student = new Student("seungbeom", "son");
		session.save(student);
		
		// session.connection().setAutoCommit(true);
		
		// commiting the transaction
		tx.commit(); // tx.rollback();
		session.close();
	}
	
	@Test
	public void testSelect() {
		Configuration config = new Configuration();
		config.configure();
		
		// obtaining a session object
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.openSession();
		
		// starting a transaction
		//Transaction tx = session.beginTransaction();
		
		// persisting...
		// load
		Student student = (Student) session.load(Student.class, 1);
		assertThat(student.getFirstName(), is("Andrew"));
		assertThat(student.getLastName(), is("White"));
		
		// createQuery
		List<Student> list = session.createQuery("from Student as s where s.id=2").list();
		Student actual = list.get(0);
		assertThat(actual.getFirstName(), is("seungbeom"));
		assertThat(actual.getLastName(), is("son"));
		
		// Criteria
		Criteria criteria = session.createCriteria(Student.class);
		criteria.add(Restrictions.idEq(new Integer(2)));
		List<Student> result = criteria.list();
		Student s = result.get(0);
		assertThat(s.getFirstName(), is("seungbeom"));
		assertThat(s.getLastName(), is("son"));
		
		
		// session.connection().setAutoCommit(true);
		
		// commiting the transaction
		//tx.commit(); // tx.rollback();
		session.close();
	}

}
