package com.Jan.mybatisTest;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.Jan.controller.GoodsController;
import com.Jan.mappers.GoodsInfoMapper;
import com.Jan.mappers.UserMapper;
import com.Jan.may.GoodsInfo;
import com.Jan.model.User;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-mvc.xml" })
public class JanuaryTest {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new GoodsController()).build();
	}

	@Test
	public void tttt() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.mockMvc.perform(MockMvcRequestBuilders.post("/goods/details").param("goodsId", "1"));
		System.out.println(">>>>>>>>>>>>>");
	}
}
