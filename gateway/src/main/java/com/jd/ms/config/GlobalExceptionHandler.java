package com.jd.ms.config;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.jds.mc.application_api.model.res.Constants;
import com.jds.mc.application_api.model.res.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/** 拦截全局异常类 */
@Slf4j
public class GlobalExceptionHandler {
  // 全局异常拦截（拦截项目中的所有异常）
  @ResponseBody
  @ExceptionHandler
  public Result<?> handlerException(Exception e) {
    log.error("Global Exception" + e.getMessage(), e);

    // 不同异常返回不同状态码
    Result<?> re = null;
    // 普通异常, 输出：500 + 异常信息
    re = new Result<>().fail(e.getMessage());
    if (e instanceof NotLoginException) {
      // 如果是未登录异常
      re =
          new Result<>()
              .customized(
                  Constants.ResponseCode.OAUTH_TOKEN_FAILURE.getCode(),
                  Constants.ResponseMessage.OAUTH_TOKEN_MISSING.getMessage(),
                  null);
    }
    if (e instanceof NotRoleException exception) { // 如果是角色异常
      re =
          new Result<>()
              .customized(
                  Constants.ResponseCode.OAUTH_TOKEN_DENIED.getCode(),
                  "无此角色：" + exception.getRole(),
                  null);
    }
    if (e instanceof NotPermissionException exception) { // 如果是权限异常
      re =
          new Result<>()
              .customized(
                  Constants.ResponseCode.OAUTH_TOKEN_DENIED.getCode(),
                  "无此角色：" + exception.getCode(),
                  null);
    }
    if (e instanceof DisableLoginException exception) { // 如果是被封禁异常
      re =
          new Result<>()
              .customized(
                  Constants.ResponseCode.USER_LOCK.getCode(),
                  "账号被封禁：" + exception.getDisableTime() + "秒后解封",
                  null);
    }

    // 返回给前端
    return re;
  }
}
