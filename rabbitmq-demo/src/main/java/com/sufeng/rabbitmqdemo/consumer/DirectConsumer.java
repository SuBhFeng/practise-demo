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
public class DirectConsumer {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue, // 创建临时队列
                            exchange = @Exchange(value = "direct", type = "direct"), // 绑定交换机
                            key = {"error"}
                    )
            }
    )
    public void directReceive1(String message){
        System.out.println("directError consumer get message ====>" + message);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue, // 创建临时队列
                            exchange = @Exchange(value = "direct", type = "direct"), // 绑定交换机
                            key = {"info", "warning", "error"}
                    )
            }
    )
    public void directReceive2(String message){
        System.out.println("directCommon consumer get message ====>" + message);
    }

}
