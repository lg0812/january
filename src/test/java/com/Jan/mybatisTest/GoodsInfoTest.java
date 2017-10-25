package com.Jan.mybatisTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Jan.mappers.GoodsInfoMapper;
import com.Jan.may.GoodsInfo;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-mvc.xml" })
public class GoodsInfoTest {
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	public GoodsInfoTest() {
		// TODO Auto-generated constructor stub
	}

	public GoodsInfo getGoodsInfo(Long id) {
		return this.goodsInfoMapper.getGoodsInfoById(id);
	}

	@Test
	public void tt() {
		System.out.println(JSON.toJSONString(this.getGoodsInfo(1L)));
	}
}
