package com.jds.mc;

import com.google.gson.Gson;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Getter
@Slf4j
public abstract class SpringBootBaseTest {
  @Resource private MockMvc mockMvc;
  @Resource private Gson gson;
}
