package com.patsnap.dubbo_zookeeper_demo.service.impl;

import com.patsnap.dubbo_zookeeper_demo.service.PantentService;
import com.patsnap.dubbo_zookeeper_demo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Reference  // 远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    PantentService pantentService;

    @Override
    public void getPantent() {
        String pantent = pantentService.getPantent();
        System.out.println("在注册中心获得:"+pantent);
    }
}
