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
public class Consumer1 {
    /**
     * 为了提高可靠性  则不能自动应答 必须处理完手动应答 且每次只能处理一条消息
     * 自动应答：默认轮询将消息分发给消费者，如果自动响应则当前消费者会先应答，然后再处理，这样就可能导致消息未处理导致丢失
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        channel.queueDeclare("hello", true, false, false, null);
        // 自动应答
//        channel.basicConsume("hello", true, new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("consumer1 get " + new String(body));
//            }
//        });
        // 一次只能接受一条未确认的消息
        channel.basicQos(1);
        // 手动应答
        channel.basicConsume("hello", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer1 get " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
