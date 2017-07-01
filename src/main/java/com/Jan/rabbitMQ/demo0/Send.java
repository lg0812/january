package com.Jan.rabbitMQ.demo0;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String QUEUE_NAME = "hello";
	private final static String host = "106.14.136.160";

	public static void main(String[] argv) throws Exception {
		// 通过工厂创建一个到服务器的连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setUsername("LG0812");
		factory.setPassword("aliyun_yltfy");
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		// 声明一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}
}