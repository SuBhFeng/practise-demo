package com.sufeng.custommq;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/27 17:35
 */
public class ConsumerClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        MqClient mqClient = new MqClient();
        String msg = mqClient.consumer();
        while (true){
            Thread.sleep(200l);
            if((msg = mqClient.consumer()) == null && mqClient.consumer().equals("null")){
                continue;
            }
            System.out.println("消费了消息" + msg);
        }
    }
}
