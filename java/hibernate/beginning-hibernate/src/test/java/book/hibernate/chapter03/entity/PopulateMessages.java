package book.hibernate.chapter03.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PopulateMessages {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void pupulateMessages() {
		Session session = sessionFactory.getCurrentSession();
		Message m = new Message("Hibernated a message on " + new Date());
		session.save(m);
		session.flush();
	}
	
	@Test
	@Transactional
	public void listMessages() {
		Session session = sessionFactory.getCurrentSession();
		List<Message> messages = session.createQuery("from Message").list();
		System.out.println("Found " + messages.size() + " message(s):");
		
		for (Message m : messages) {
			System.out.println(m.getMessage());
		}
	}
	
}
