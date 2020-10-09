package com.sufeng.rabbitmqdemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class RabbitmqDemoApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// hello world
	@Test
	void testHello() {
		rabbitTemplate.convertAndSend("hello","hello world");
	}

	// work模式测试
	@Test
	void testWork() {
		for (int i = 0; i < 10; i++) {
			rabbitTemplate.convertAndSend("work","work message " + i);
		}
	}

	// fanout模式测试
	@Test
	void testFanout() {
		rabbitTemplate.convertAndSend("logs","","fanout message");
	}

	// direct模式测试
	@Test
	void testDirect() {
		String routingKey = "error";
		rabbitTemplate.convertAndSend("direct",routingKey,"direct routingKey:[" + routingKey +"] message");
	}

	// topic模式测试
	@Test
	void testTopic() {
		String routingKey = "user.add";
		rabbitTemplate.convertAndSend("topic", routingKey,"topic routingKey:[" + routingKey +"] message");
	}


}
