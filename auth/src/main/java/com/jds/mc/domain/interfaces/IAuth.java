package com.jds.mc.domain.interfaces;

import com.jds.mc.domain.model.User;

/**
 * @author jensen_deng
 */
public interface IAuth {
  void login(User domain);
}
