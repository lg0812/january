package com.Jan.mybatisTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "/app-config.xml", "/test-data-access-config.xml" })
@Transactional
public class StartDemo {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public User getUser(String userId) {
		return (User) sqlSession.selectOne("org.mybatis.spring.sample.mapper.UserMapper.getUser", userId);
	}

}
