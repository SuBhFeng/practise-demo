package com.sufeng.rabbitmqdemo.demo.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 15:34
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        String exchangeName = "logs_direct";
        // 声明交换机
        channel.exchangeDeclare(exchangeName,"direct");
        // 声明临时队列
        String queue = channel.queueDeclare().getQueue();
        // 绑定交换机的指定routting key
        channel.queueBind(queue,exchangeName,"error");
        channel.queueBind(queue,exchangeName,"info");
        channel.queueBind(queue,exchangeName,"warning");
        // 消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("common consumer get ===> " + new String(body));
            }
        });



    }
}
