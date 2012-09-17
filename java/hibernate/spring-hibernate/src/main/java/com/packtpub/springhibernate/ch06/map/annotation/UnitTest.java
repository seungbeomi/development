package com.packtpub.springhibernate.ch06.map.annotation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

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
		Map<String, String> papers = actual.getPapers();
		
		for (Map.Entry<String, String> paper : papers.entrySet()) {
			assertThat(paper.getKey(), is("Title1"));
			assertThat(paper.getValue(), is("Paper1.doc"));
		}
		
		// --------------------------------------------------------
		Student actual2 = (Student) session.get(Student.class, 2);
		
		assertThat(actual2.getFirstName(), is("Robert"));
		Map<String, String> papers2 = actual2.getPapers();
		
		assertThat(papers2.get("Title2"), is("Paper2.html"));
		assertThat(papers2.get("Title3"), is("Paper3.doc"));
		
		session.close();
	}
}
