package com.sufeng.rabbitmqdemo.demo.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 16:29
 */
public class Provider {
    /**
     * Routting模式  --- 1 topic(订阅模式)
     * 作用：不同的消费者消费不同的消息 动态路由
     * 这种模型的routting key 一般由一个或多个单词组成，多个单词用.分割。例如：item.insert
     * 支持统配符：* 匹配一个词  # 匹配零到多个词
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        String exchangeName = "topics";
        // 声明交换机
        channel.exchangeDeclare(exchangeName,"topic");
        // routting key
        String routeKey = "aaa.user.save";
        // 发布消息
        channel.basicPublish(exchangeName,routeKey,null,("topic msg routting key [" + routeKey +"]").getBytes());
        // 关闭连接
        RabbitMqUtils.closeConnection(channel,connetion);
    }
}
