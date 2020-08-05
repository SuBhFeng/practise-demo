package com.patsnap.redissondemo.controller;


import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/redis")
public class RedisController {

    @Autowired
    RedissonClient redisson;

    @GetMapping("/redistest")
    public String redisTest(){
        RBucket<Object> num7 = redisson.getBucket("num7");
        return num7.get().toString();
    }
}
