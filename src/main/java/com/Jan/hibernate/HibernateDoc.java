package com.Jan.hibernate;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-mysql-dev.xml")
@Transactional
@Commit
public class HibernateDoc {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Test
	public void test1() {
		System.out.println(sessionFactory);
		sessionFactory.getCurrentSession().createQuery("from UserToken").list();
	}
}
