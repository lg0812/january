package com.Jan.controller;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/jedis")
public class JedisController {
	private static Logger logger = LogManager.getLogger(JedisController.class.getName());
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/getFoo")
	@ResponseBody
	public String getFoo() {
		String str = JSON.toJSONString(stringRedisTemplate.opsForValue().get("foo"));
		logger.info(str);
		logger.debug(System.getenv());
		logger.debug(System.getProperties());
		return str;
	}
}
