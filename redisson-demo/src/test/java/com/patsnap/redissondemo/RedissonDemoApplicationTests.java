package com.patsnap.redissondemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class RedissonDemoApplicationTests {

	@Autowired
	RedissonClient redissonClient;

	@Test
	void contextLoads() {
		System.out.println(redissonClient);
		RBucket<Object> num7 = redissonClient.getBucket("num7");
		System.out.println("num7:"+num7.get().toString());
	}

}
