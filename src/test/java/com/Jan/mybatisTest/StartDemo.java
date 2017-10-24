package com.Jan.mybatisTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Jan.mappers.GoodsInfoMapper;
import com.Jan.mappers.UserMapper;
import com.Jan.may.GoodsInfo;
import com.Jan.model.User;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-mvc.xml" })
public class StartDemo {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	public User getUser(Long id) {
		return this.userMapper.getUser(id);
	}

	public List<User> getUsers(String name) {
		return this.userMapper.getUsers(name);
	}

	public GoodsInfo getGoodsInfo(Long id) {
		return this.goodsInfoMapper.getGoodsInfoById(id);
	}

	// @Test
	public void ttt() {
		System.out.println(JSON.toJSONString(getUser(1L)));
	}

	// @Test
	public void tttt() {
		System.out.println(JSON.toJSONString(getGoodsInfo(1L)));
	}

	@Test
	public void ttttt() {
		System.out.println(">>>>>>>");
		System.out.println(JSON.toJSONString(getUsers("%LG%")));
	}

}
