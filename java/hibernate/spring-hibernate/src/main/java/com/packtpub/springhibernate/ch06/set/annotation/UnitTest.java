package com.packtpub.springhibernate.ch06.set.annotation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testComponent () {
		SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class)
			.buildSessionFactory();
		Session session = factory.openSession();
		
		// --------------------------------------------------------
		Student actual = (Student) session.get(Student.class, 1);

		assertThat(actual.getFirstName(), is("John"));
		Set<String> papers = actual.getPapers();
		for (String s : papers) {
			assertThat(s, is("Paper1.doc"));
		}
		
		// --------------------------------------------------------
		Student actual2 = (Student) session.get(Student.class, 2);
		
		assertThat(actual2.getFirstName(), is("Robert"));
		Set<String> papers2 = actual2.getPapers();
		int i = 0;
		for (String s : papers2) {
			if (i == 0) assertThat(s, is("Paper3.doc"));
			if (i == 1) assertThat(s, is("Paper2.html"));
			i++;
		}
		
		
		session.close();
	}
}
