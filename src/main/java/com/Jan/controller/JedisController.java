package com.Jan.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/jedis")
public class JedisController {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/getFoo")
	@ResponseBody
	public String getFoo() {
		return JSON.toJSONString(stringRedisTemplate.opsForValue().get("foo"));
	}
}
