package com.Jan.mybatisTest;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.Jan.mappers.UserMapper;
import com.Jan.model.User;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:spring-mvc.xml" })
@Transactional
public class StartDemo {

	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User getUser(Long id) {
		return this.userMapper.getUser(id);
	}

	@Test
	public void ttt() {
		System.out.println(JSON.toJSONString(getUser(1L)));
	}

}
