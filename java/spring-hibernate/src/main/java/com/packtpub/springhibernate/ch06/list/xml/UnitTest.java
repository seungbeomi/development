package com.packtpub.springhibernate.ch06.list.xml;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class UnitTest {

	@Test
	public void testComponent () {
		SessionFactory factory = new Configuration()
			.configure("/com/packtpub/springhibernate/ch06/hibernate.cfg.xml")
			.addClass(Student.class)
			.buildSessionFactory();
		Session session = factory.openSession();
		
		// --------------------------------------------------------
		Student actual = (Student) session.load(Student.class, 1);

		assertThat(actual.getFirstName(), is("John"));
		List<String> papers = actual.getPapers();
		String s = papers.get(1);	// position : 1
		assertThat(s, is("Paper1.doc"));
		
		// --------------------------------------------------------
		Student actual2 = (Student) session.load(Student.class, 2);
		
		assertThat(actual2.getFirstName(), is("Robert"));
		List<String> papers2 = actual2.getPapers();
		assertThat(papers2.get(2), is("Paper2.html"));	// position : 2
		assertThat(papers2.get(3), is("Paper3.doc"));	// position : 3
		
		session.close();
	}
}
