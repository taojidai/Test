package com.biz4.rabbitmq;

import java.io.IOException;  
  
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
public class Send {  
  
    public static void main(String[] args) throws IOException {  
        // 创建一个连接连接服务器  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //String queueName = "queue01";   
        
                
        //为Channel定义queue的属性，queueName为queue名称 ，第二个属性设置queue是否持久化  
        channel.queueDeclare("queue02", false, false,true,null); 
        channel.exchangeDeclare("chatroom","topic");  
          
        String message = "hi all friends";  
        channel.basicPublish("chatroom", "*.friends", null, message.getBytes());  
        System.out.println(" [x] Sent '" + "*.friends" + "':'" + message +"'");  
          
        message = "bye all enemies";  
        channel.basicPublish("chatroom", "*.enemies", null, message.getBytes());  
        System.out.println(" [x] Sent '" + "*.enemies" + "':'" + message +"'");  
          
        message = "delete all females friends or enemies";  
        channel.basicPublish("chatroom", "female.#", null, message.getBytes());  
        System.out.println(" [x] Sent '" + "female.#" + "':'" + message +"'");  
          
        channel.close();  
        connection.close();  
    }  
} 
