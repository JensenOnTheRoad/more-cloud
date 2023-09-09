package com.jds.mc.application_api.controller;

import com.jds.mc.application_api.model.req.LoginReq;
import com.jds.mc.domain.interfaces.IAuth;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jensen_deng
 */
@RestController
@RequestMapping("/auth")
@Transactional(rollbackFor = Exception.class)
public class AuthController {
  @Resource IAuth auth;

  @PostMapping
  public void login(@RequestBody @Validated LoginReq req) {
    req.login(auth);
  }
}
