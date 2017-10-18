package com.Jan.Aspect;

import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Jan.service.CacheService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-mvc.xml" })
public class CacheAspect {

	public CacheAspect() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public CacheService cacheService;

	@Test
	public void test1() {
		cacheService.testStr();
		System.out.println("---------------------------------------------------------------");
	}

}
