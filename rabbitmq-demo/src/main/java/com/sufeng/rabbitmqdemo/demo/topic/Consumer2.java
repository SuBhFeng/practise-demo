package com.sufeng.rabbitmqdemo.demo.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 16:59
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        // 声明交换机
        String exchangeName = "topics";
        channel.exchangeDeclare(exchangeName,"topic");
        // 声明临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列接收指定路由的信息
        String routtingKey = "#.user.*";
        channel.queueBind(queue,exchangeName,routtingKey);
        // 消费信息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("#.user.* consumer get ===> " + new String(body));
            }
        });

    }
}
