package com.jds.mc;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Getter
public abstract class SpringBootBaseTest  {
  @Resource private MockMvc mockMvc;
  @Resource private Gson gson;

  @Test
  void test_happy_path() {}
}
