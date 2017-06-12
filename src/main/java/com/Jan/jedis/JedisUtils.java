package com.Jan.jedis;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-jedis.xml")
public class JedisUtils {
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	// 以下使用方法需要先在xml中声明bean:redisTemplate
	@Test
	public void getFromRedis() {
		String str = (String) redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				// TODO Auto-generated method stub
				return new String(connection.get(redisTemplate.getStringSerializer().serialize("foo")));
			}
		});

		System.out.println(str);
	}

	// 在xml中声明stringRedisTemplate
	@Test
	public void getFromStringRedis() {
		System.out.println(stringRedisTemplate.opsForValue().get("foo"));
	}

	@Test
	public void jedisOnly() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("192.168.0.104");
		System.out.println("Connection to server sucessfully");
		// 设置 redis 字符串数据
		// jedis.set("runoobkey", "Redis tutorial");
		// 获取存储的数据并输出
		System.out.println("Stored string in redis:: " + jedis.get("foo"));
	}
}
