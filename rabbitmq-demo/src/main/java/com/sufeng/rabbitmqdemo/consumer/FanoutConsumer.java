package com.sufeng.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiekangkang
 * @date 2020/9/18 10:46
 */
@Component
public class FanoutConsumer {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue, // 创建临时队列
                            exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
                    )
            }
    )
    public void workReceive1(String message){
        System.out.println("fanout consumer1 get message ====>" + message);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue, // 创建临时队列
                            exchange = @Exchange(value = "logs", type = "fanout") // 绑定交换机
                    )
            }
    )
    public void workReceive2(String message){
        System.out.println("fanout consumer2 get message ====>" + message);
    }

}
