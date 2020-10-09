package com.sufeng.rabbitmqdemo.demo.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xiekangkang
 * @date 2020/9/17 10:58
 */
public class Provider {
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
        // 声明连接队列  参数1：队列名称  参数2：是否持久化  参数3：是否独占队列  参数4：是否自动删除  参数5：其他属性
        channel.queueDeclare("hello",true,false,false,null);
        // 发送消息
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());
        // 关闭连接
        channel.close();
        connection.close();
    }
}
