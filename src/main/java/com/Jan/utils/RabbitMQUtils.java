package com.Jan.utils;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/RabbitMQUtils")
@Transactional
public class RabbitMQUtils {
	@Resource(name = "amqpTemplate")
	public RabbitTemplate rabbitTemplate;

	@RequestMapping("/send_info")
	@ResponseBody
	public String sendInfo(String info) {
		for (int t = 0; t < 100; t++)
			rabbitTemplate.convertAndSend(info + "-------->" + t);
		return "success";
	}

}
