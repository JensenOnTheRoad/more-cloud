package com.jds.mc.application_api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.jds.mc.SpringBootBaseTest;
import com.jds.mc.application_api.model.req.LoginReq;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author jensen_deng
 */
class AuthControllerTest extends SpringBootBaseTest {

  private static Gson gson;
  private static MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    gson = getGson();
    mockMvc = getMockMvc();
  }

  @AfterEach
  void tearDown() {}

  @Test
  @DisplayName("登陆测试")
  void login() throws Exception {
    String content = gson.toJson(LoginReq.builder().build());

    MockHttpServletRequestBuilder request =
        MockMvcRequestBuilders.post("/auth")
            .content(content)
            .contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(request).andExpect(status().isOk());
  }
}
