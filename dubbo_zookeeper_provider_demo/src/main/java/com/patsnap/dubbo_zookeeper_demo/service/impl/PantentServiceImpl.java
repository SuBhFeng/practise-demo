package com.patsnap.dubbo_zookeeper_demo.service.impl;

import com.patsnap.dubbo_zookeeper_demo.service.PantentService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service   // 将服务发布出去
@Component // 放在容器中
public class PantentServiceImpl implements PantentService {
    @Override
    public String getPantent() {
        return "get a pantent";
    }
}
