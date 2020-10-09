package com.sufeng.rabbitmqdemo.demo.helloworld;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xiekangkang
 * @date 2020/9/17 11:12
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.153.150");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("sufeng");
        connectionFactory.setPassword("sufeng");
        connectionFactory.setVirtualHost("/sufeng");
        Connection connection = connectionFactory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 声明连接队列
        channel.queueDeclare("hello",true,false,false,null);
        // 消费消息  参数1：队列名称  参数2：是否自动应答   参数3：消费处理流程
        channel.basicConsume("hello",true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }

        });
    }
}
