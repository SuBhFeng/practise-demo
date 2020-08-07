package com.patsnap.dubbo_zookeeper_demo;

import com.patsnap.dubbo_zookeeper_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DubboZookeeperConsumerDemoApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        userService.getPantent();
    }

}
