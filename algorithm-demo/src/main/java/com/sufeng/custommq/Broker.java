package com.sufeng.custommq;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xiekangkang
 * @date 2020/9/27 16:22
 * 消息中心存储服务
 */
public class Broker {
    // 队列存储的最大数量
    private final static int MAX_SIZE = 3;

    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    /**
     * 生产消息
     * @param msg
     */
    public static void produce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("成功向消息处理中心投递消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        }else{
            System.out.println("消息处理中心内暂存的消息达到最大负荷，不能继续放入消息！");
        }
        System.out.println("=======================");
    }

    /**
     * 消费消息
     * @return
     */
    public static String consumer(){
        String msg = messageQueue.poll();
        if(msg != null){
            // 消费条件满足情况，从消息容器中取出一条消息
            System.out.println("已经消费消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            System.out.println("消息处理中心内没有消息可供消费！");
        }
        System.out.println("=======================");
        return msg;
    }
}
