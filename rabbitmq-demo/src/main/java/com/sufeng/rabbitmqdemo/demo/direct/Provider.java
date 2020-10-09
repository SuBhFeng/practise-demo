package com.sufeng.rabbitmqdemo.demo.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sufeng.rabbitmqdemo.demo.utils.RabbitMqUtils;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/17 15:34
 */
public class Provider {
    /**
     * Routting模式  --- 1 direct(直连模式)
     * 作用：不同的消费者消费不同的消息 静态路由
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Connection connetion = RabbitMqUtils.getConnetion();
        Channel channel = connetion.createChannel();
        // 声明交换机 参数1：交换机名称  参数2：指定交换机类型(direct为指定Routing key转发)
        channel.exchangeDeclare("logs_direct","direct");
        // 指定routting key
        String key = "error";
        // 发布消息
        channel.basicPublish("logs_direct",key,null,("指定routting key:[" + key + "] 的msg").getBytes());
        // 关闭连接
        RabbitMqUtils.closeConnection(channel,connetion);
    }
}
