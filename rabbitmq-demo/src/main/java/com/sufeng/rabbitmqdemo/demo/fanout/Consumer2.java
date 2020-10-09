package com.sufeng.rabbitmqdemo.demo.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 15:04
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        // 声明交换机
        channel.exchangeDeclare("logs","fanout");
        // 创建临时队列
        String queue = channel.queueDeclare().getQueue();
        // 将临时队列绑定exchange  参数1：队列名字  参数2：交换机名  参数3：指定路由(不指定为默认消息可以全部接收)
        channel.queueBind(queue,"logs","");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer2 get ===>" + new String(body));
            }
        });
    }
}
