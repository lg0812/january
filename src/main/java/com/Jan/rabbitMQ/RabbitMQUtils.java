package com.Jan.rabbitMQ;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring-rabbitmq.xml")
public class RabbitMQUtils {
	@Resource(name = "amqpTemplate")
	public RabbitTemplate rabbitTemplate;

	@Test
	public String sendInfo(String info) {
		for (int t = 0; t < 100; t++)
			rabbitTemplate.convertAndSend(info + "-------->" + t);
		return "success";
	}

}
