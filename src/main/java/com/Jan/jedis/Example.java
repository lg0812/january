package com.Jan.jedis;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.Jan.model.User;
import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-jedis-dev.xml")
public class Example {

	/*
	 * 以下是比较简单的使用方法，在xmL中定义 bean:redisTemplate; 然后使用以下两行代码即可
	 */
	@Autowired
	private RedisTemplate<String, String> template;

	// inject the template as ListOperations
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valOps;

	@Resource(name = "redisTemplate")
	// 以下两种写法均可
	// private HashOperations<String, String, String> hashOps;
	private HashOperations hashOps;

	@Resource(name = "redisTemplate")
	private ListOperations listOps;

	@Test
	public void addLink() {
		System.out.println(valOps);
		valOps.set("foo", "sunday1");
		System.out.println(valOps.get("foo"));
	}

	// 以下是关于HashOperations的使用
	// hash
	@Test
	public void writeHash() {
		HashMapper<User, String, String> mapper = new BeanUtilsHashMapper<User>(User.class);
		User user = new User("LG0812", "password none", "email none");
		Map<String, String> map = mapper.toHash(user);
		hashOps.putAll("LG0812", map);
	}

	@Test
	public void loadHash() {
		HashMapper<User, String, String> mapper = new BeanUtilsHashMapper<User>(User.class);
		Map<String, String> map = hashOps.entries("LG0812");
		System.out.println(JSON.toJSONString(mapper.fromHash(map)));
	}

	// 以下是关于ListOperations的使用
	@Test
	public void writeList() {
		listOps.leftPush("userId", "id is 3");
		listOps.rightPush("userId", "id is 3");
	}

	@Test
	public void loadList() {
		System.out.println(listOps.leftPop("userId"));
		System.out.println(listOps.index("userId", 1));
	}
}
