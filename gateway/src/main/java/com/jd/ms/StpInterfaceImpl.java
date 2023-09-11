package com.jd.ms;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {

  /** 当前全部是模拟数据，真实情况使用根据loginId动态查询对应角色和权限 */
  @Override
  public List<String> getPermissionList(Object loginId, String loginType) {
    // 返回此 loginId 拥有的权限列表
    List<String> strs = new ArrayList<>();
    strs.add("system");
    return strs;
  }

  @Override
  public List<String> getRoleList(Object loginId, String loginType) {
    // 返回此 loginId 拥有的角色列表
    List<String> strs = new ArrayList<>();
    strs.add("admin");
    return strs;
  }
}
