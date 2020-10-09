package com.sufeng.rabbitmqdemo.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xiekangkang
 * @date 2020/9/18 10:46
 */
@Component
public class WorkConsumer {
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void workReceive1(String message){
        System.out.println("work consumer1 get message ====>" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void workReceive2(String message){
        System.out.println("work consumer2 get message ====>" + message);
    }

}
