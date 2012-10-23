package kr.co.tsb.comp.batch.admin.dao;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class QrtzJobDetailsRepositoryTest {

	@Autowired QrtzJobDetailsRepository qrtzJobDetailsRepository;
	@Autowired SessionFactory sessionFactory;
	
	@Test
	public void test() {
		assertNotNull(qrtzJobDetailsRepository);
		assertNotNull(sessionFactory);
	}

}
