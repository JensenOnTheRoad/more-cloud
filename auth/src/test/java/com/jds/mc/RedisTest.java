package com.jds.mc;

import com.jds.mc.infrastucture.utils.RedisUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RedisTest {

  @Resource private RedisUtils redisUtils;

  @Test
  @DisplayName("redis测试")
  public void test() {
    String key = "testKey";
    String content = "redis content ";
    redisUtils.set(key, content);

    String result = redisUtils.get(key, String.class);
    log.info("=== key= {}, value= {} ===", key, result);
  }
}
