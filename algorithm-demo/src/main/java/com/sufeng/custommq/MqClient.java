package com.sufeng.custommq;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author xiekangkang
 * @date 2020/9/27 17:14
 * 访问消息队列的客户端
 */
public class MqClient {

    public static void produce(String msg) throws IOException {
        // 创建socket与ServerSocker连接
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
            bw.write(msg);
            bw.flush();
        }
    }

    public static String consumer() throws IOException {
        // 创建socket与ServerSocket连接
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.SERVICE_PORT);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ){
            // 先发送要消费信息协议
            bw.write("CONSUMER:\n");
            bw.flush();
            // 从消息队列中拿到一条消息
            String s = br.readLine();
            return s;
        }
    }
}
