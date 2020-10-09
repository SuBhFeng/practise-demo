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
public class TopicConsumer {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue, // 创建临时队列
                            exchange = @Exchange(value = "topic", type = "topic"), // 绑定交换机
                            key = {"user.*", "admin.*"} // 接受指定路由的队列信息
                    )
            }
    )
    public void directReceive1(String message){
        System.out.println("topic-user.* consumer get message ====>" + message);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue, // 创建临时队列
                            exchange = @Exchange(value = "topic", type = "topic"), // 绑定交换机
                            key = {"#.user.*","#.admin.*"} // 接受指定路由的队列信息
                    )
            }
    )
    public void directReceive2(String message){
        System.out.println("topic-#.user.* consumer get message ====>" + message);
    }

}
