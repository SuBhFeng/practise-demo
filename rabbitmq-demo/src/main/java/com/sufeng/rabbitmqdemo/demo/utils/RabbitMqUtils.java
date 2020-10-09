package com.sufeng.rabbitmqdemo.demo.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xiekangkang
 * @date 2020/9/17 10:59
 */
public class RabbitMqUtils {

    private static ConnectionFactory connectionFactory;

    static{
        // 初始化连接工厂信息
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.153.150");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("sufeng");
        connectionFactory.setPassword("sufeng");
        connectionFactory.setVirtualHost("/sufeng");
    }
    /**
     * 获取连接
     * @return
     */
    public static Connection getConnetion(){
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     * @param channel
     * @param connection
     */
    public static void closeConnection(Channel channel, Connection connection){
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
