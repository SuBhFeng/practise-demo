package com.sufeng.custommq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xiekangkang
 * @date 2020/9/27 16:46
 * 消息处理中心处理数据逻辑
 */
public class BrokerServer implements Runnable{

    public final static int SERVICE_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        // 启动消息处理中心处理数据
        ServerSocket serverSocket = new ServerSocket(SERVICE_PORT);
        while(true){
            BrokerServer brokerServer = new BrokerServer(serverSocket.accept());
            new Thread(brokerServer).start();
        }
    }

    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ){
            while(true){
                String str = br.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("接受到原数据" + str);
                // 判断是否符合自定义协议
                if(str.contains("CONSUMER:")){
                    // 从消息队列中消费消息
                    String consumerStr = Broker.consumer();
                    bw.write(consumerStr + "\n");
                    bw.flush();
                }else if(str.contains("SEND:")){
                    // 向消息队列中存储消息
                    Broker.produce(str);
                }else{
                    System.out.println("原始数据:"+str+"没有遵循协议,不提供相关服务");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
