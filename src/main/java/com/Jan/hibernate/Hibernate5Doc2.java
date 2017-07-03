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

import com.Jan.model.UserToken;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-mysql-hibernate5.xml")
@Transactional
@Commit
public class Hibernate5Doc2 {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Test
	public void test1() {
		System.out.println(sessionFactory.getCurrentSession().createQuery("from UserToken").list().get(0));

	}
}
