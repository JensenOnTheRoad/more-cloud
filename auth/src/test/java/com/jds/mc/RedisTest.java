package com.jds.mc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
  @Autowired private RedisTemplate<String, Object> redisTemplate;

  @Test
  public void test() {
    String content = "redis content ";
    redisTemplate.boundValueOps("userKey").set(content);

    String rs = (String) redisTemplate.boundValueOps("userKey").get();
    System.out.println("rs = " + rs);

    String rs2 = (String) redisTemplate.opsForValue().get("userKey");
    System.out.println("rs2 = " + rs2);
  }
}
