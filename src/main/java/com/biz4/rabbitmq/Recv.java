package com.biz4.rabbitmq;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownSignalException;

public class Recv {

	public static void main(String[] args) throws IOException,
			ShutdownSignalException, ConsumerCancelledException,
			InterruptedException {
		// 创建一个连接接收数据
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//

		// 等待消息

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Chatroom");
		System.out.println("Type q to exit..");

		String name = null;
		while (true) {
			System.out.println("Input your nickname");
			name = scanner.nextLine();
			if (!StringUtils.isBlank(name)) {
				break;
			}
			
		}
		System.out
				.println("Hello " + name + ",you can chat from now, enjoy it");

		String queueName = name;
		channel.exchangeDeclare("chatroom", "topic");
		// 为Channel定义queue的属性，queueName为queue名称 ，第二个属性设置queue是否持久化
		channel.queueDeclare(queueName, false, false, true, null);
		// 用于通过绑定bindingKey将queue到Exchange，之后便可以进行消息接收
		channel.queueBind(queueName, "chatroom", "female.*");
		channel.queueBind(queueName, "chatroom", "male.*");

		// System.out.println(a);

		while (true) {
			String message1 = " ";
			message1 = name + " said " + scanner.nextLine();
			if (message1.equals(name + " said " + "q")) {
				System.out.println("bye");
				channel.close();
				connection.close();
				break;
			}

			channel.basicPublish("chatroom", "female.*", null,
					message1.getBytes());
			/*
			 * System.out.println(name + " said " + message1 + "'");
			 */

			// QueueingConsumer consumer = new QueueingConsumer(channel);

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag,
						Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(message);
				}
			};
			channel.basicConsume(queueName, false, consumer);

		}
	}
}
