package com.sufeng.rabbitmqdemo.demo.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 15:02
 */
public class Provider {
    /**
     * 散出模式 类似于广播  每一条消息所有队列都可接收到
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        // 声明交换机  发布者通过交换机将消息转发给队列  参数1：交换机名称  参数2：指定交换机类型 (fanout 为广播模式)
        channel.exchangeDeclare("logs","fanout");
        // 发布消息
        channel.basicPublish("logs","",null,"fanout msg".getBytes());
        RabbitMqUtils.closeConnection(channel,connetion);
    }
}
