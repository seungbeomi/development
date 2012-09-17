package com.packtpub.springhibernate.ch06.onetomany.xml;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

@Data
public class UnitTest {

	@Test
	public void testCase () {
		SessionFactory factory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addClass(School.class)
			.addClass(Student.class)
			.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------------------------------------
		
		List<Student> students = new ArrayList<Student>();
		Student s1 = new Student("student1");
		Student s2 = new Student("student2");
		students.add(s1);
		students.add(s2);
		
		School school = new School("school");
		school.setStudents(students);
		
		session.save(school);
		
		School s = (School) session.get(School.class, new Integer(1));
		List<Student> l = s.getStudents();
		assertThat(l.size(), is(2));
		assertThat(l.get(0).getFirstName(), is("student1"));
		assertThat(l.get(1).getFirstName(), is("student2"));
		
		// -------------------------------------------------------------------------------
		tx.commit();
		session.close();
	}
	
}
