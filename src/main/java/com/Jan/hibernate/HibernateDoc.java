package com.Jan.hibernate;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-mysql.xml")
@Transactional(transactionManager="transactionManager")
@Commit
public class HibernateDoc {
	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void test1() {
		System.out.println(sessionFactory);
		// UserToken ut = (UserToken)
		// sessionFactory.getCurrentSession().createQuery("from
		// UserToken").list();
		// System.out.println(JSON.toJSONString(ut));
	}
}
