package com.sufeng.rabbitmqdemo.demo.workqueue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 14:22
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        channel.queueDeclare("hello", true, false, false, null);
        // 自动应答
//        channel.basicConsume("hello", true, new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                // 模拟性能较低  延时处理
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("consumer2 get " + new String(body));
//            }
//        });
        // 一次只能接受一条未确认的消息
        channel.basicQos(1);
        // 手动应答
        channel.basicConsume("hello", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 模拟性能较低  延时处理
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("consumer2 get " + new String(body));
                // 参数1：本次处理完成的消息tag  参数2：是否可以一次处理多个消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
