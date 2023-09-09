package com.jds.mc.application_api.model.req;

import com.jds.mc.domain.interfaces.IAuth;
import com.jds.mc.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jensen_deng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReq {

  private String account;

  private String password;

  public void login(IAuth auth) {
    auth.login(User.builder().username(account).password(password).build());
  }
}
