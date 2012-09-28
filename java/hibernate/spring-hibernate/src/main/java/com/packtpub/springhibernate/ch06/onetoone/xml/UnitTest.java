package com.packtpub.springhibernate.ch06.onetoone.xml;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Ignore;
import org.junit.Test;

public class UnitTest {

	@Ignore @Test
	public void test01() {
		SessionFactory factory = new Configuration()
				.configure("/com/packtpub/springhibernate/ch06/hibernate.cfg.xml")
				.addClass(Student.class)
				.addClass(Phone.class)
				.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		// --------------------------------------------------------
		Student student = new Student("seungbeomi");
		Phone phone = new Phone("seungbeomi's phone number","000-111-2222");
		student.setPhone(phone);
		
		session.save(student);
		
		tx.commit();
		session.close();
	}
	
	@Test
	public void test02() {
		SessionFactory factory = new Configuration()
				.configure("/com/packtpub/springhibernate/ch06/hibernate.cfg.xml")
				.addClass(Student.class)
				.addClass(Phone.class)
				.buildSessionFactory();
		Session session = factory.openSession();

		// --------------------------------------------------------
		// Student가 가지는 Phone이 조회가능
		Student loadStudent = (Student) session.load(Student.class, 1);
		assertThat(loadStudent.getFirstName(), is("seungbeomi"));
		Phone loadPhone = loadStudent.getPhone();
		assertThat(loadPhone.getComment(), is("seungbeomi's phone number"));
		assertThat(loadPhone.getNumber(), is("000-111-2222"));
		
		session.close();
	}
	
	@Test
	public void test03() {
		SessionFactory factory = new Configuration()
				.configure("/com/packtpub/springhibernate/ch06/hibernate.cfg.xml")
				.addClass(Student.class)
				.addClass(Phone.class)
				.buildSessionFactory();
		Session session = factory.openSession();
		
		// --------------------------------------------------------
		// Phone에서 Student조회가능
		Phone loadPhone = (Phone) session.load(Phone.class, 1);
		assertThat(loadPhone.getComment(), is("seungbeomi's phone number"));
		assertThat(loadPhone.getNumber(), is("000-111-2222"));
		
		Student student = loadPhone.getStudent();
		assertThat(student.getFirstName(), is("seungbeomi"));
		
		session.close();
	}
}