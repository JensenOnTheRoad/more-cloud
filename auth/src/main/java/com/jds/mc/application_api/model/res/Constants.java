package com.jds.mc.application_api.model.res;

import lombok.Getter;

public enum Constants {
  ;

  @Getter
  public enum ResponseMessage {
    REQUEST_SUCCESS("请求成功"),
    REQUEST_FAILED("请求失败"),
    SYSTEM_ERROR("系统错误"),
    APP_EXCEPTION("应用程序异常"),
    OAUTH_TOKEN_MISSING("token 缺失"),
    OAUTH_TOKEN_ILLEGAL("验证过期，请重新登录"),
    OAUTH_TOKEN_DENIED("权限不足"),
    OAUTH_TOKEN_CHECK_ERROR("token 验证失败");

    private final String message;

    ResponseMessage(String message) {
      this.message = message;
    }
  }

  public enum ResponseCode {
    REQUEST_SUCCESS(1), // 请求成功
    REQUEST_FAILED(0), // 请求失败
    SYSTEM_ERROR(-1), // 系统错误
    OAUTH_TOKEN_FAILURE(2001), // token 无效
    OAUTH_TOKEN_MISSING(2008), // token 缺失
    OAUTH_TOKEN_DENIED(2009), // token 权限不足
    PWD_ERROR(4001), // 密码错误
    USER_LOCK(4002); // 账号锁定

    private final int code;

    public int getCode() {
      return code;
    }

    ResponseCode(int code) {
      this.code = code;
    }
  }

  public enum ResponseStatusCode {
    SUCCESSFUL_CODE(200),
    FAIL_CODE(500), // token 失效
    OAUTH_TOKEN_FAILURE(2001), // token 失效
    OAUTH_TOKEN_MISSING(2008), // token 缺失
    OAUTH_TOKEN_DENIED(2009), // token 权限不足
    PWD_ERROR(4001), // 密码错误
    USER_LOCK(4002); // 账号锁定
    private final int code;

    public int getCode() {
      return code;
    }

    ResponseStatusCode(int code) {
      this.code = code;
    }
  }
}
