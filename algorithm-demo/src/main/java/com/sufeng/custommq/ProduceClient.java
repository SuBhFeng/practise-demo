package com.sufeng.custommq;

import java.io.IOException;

/**
 * @author xiekangkang
 * @date 2020/9/27 17:30
 */
public class ProduceClient {
    public static void main(String[] args) throws IOException {
        MqClient mqClient = new MqClient();
        mqClient.produce("SEND:AAAA");
    }
}
