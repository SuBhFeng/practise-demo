package com.sufeng.rabbitmqdemo.demo.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 14:13
 */
public class Provider {
    /**
     * 一个发布者 多个消费者 轮询消费消息  可能处理消息的速度不一样  尽可能大的利用机器性能
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 创建连接
        Connection connetion = RabbitMqUtils.getConnetion();
        // 创建通道
        Channel channel = connetion.createChannel();
        // 连接队列
        channel.queueDeclare("hello",true,false,false,null);
        // 发送消息
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("","hello", null,("work msg["+ i + "]").getBytes());
        }
        // 关闭连接
        RabbitMqUtils.closeConnection(channel,connetion);
    }
}
