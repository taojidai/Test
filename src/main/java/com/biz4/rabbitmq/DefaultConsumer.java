package com.biz4.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class DefaultConsumer implements Consumer{
	public static String fanout_exchange = "dy-exchange-fanout";
	public static String queue_fanout_1 = "dy-queue-fanout-1";
	public static String queue_fanout_2 = "dy-queue-fanout-2";
	
	public void handleCancel(String arg0) throws IOException {
		
	}
	
	public void handleCancelOk(String arg0) {
		
	}
	public void handleConsumeOk(String arg0) {
		
	}
	public void handleDelivery(String arg0, Envelope arg1,
			BasicProperties arg2, byte[] arg3) throws IOException {
		
	}
	public void handleRecoverOk(String arg0) {
		
	}
	public void handleShutdownSignal(String arg0, ShutdownSignalException arg1) {
		
	}
}
