package com.jds.mc.application_api.model.req;

import com.jds.mc.domain.interfaces.IAuth;
import com.jds.mc.domain.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jensen_deng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReq {

  @NotBlank private String account;

  @NotBlank private String password;

  private String token;

  public void login(IAuth auth) {
    User domain = User.builder().username(account).password(password).build();
    auth.login(domain);
  }
}
