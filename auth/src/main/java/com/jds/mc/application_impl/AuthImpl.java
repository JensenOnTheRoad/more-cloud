package com.jds.mc.application_impl;

import com.jds.mc.domain.interfaces.IAuth;
import com.jds.mc.domain.model.Permission;
import com.jds.mc.domain.model.User;
import com.jds.mc.infrastucture.db.PermissionPO;
import com.jds.mc.infrastucture.db.UserRolePO;
import com.jds.mc.infrastucture.db.UserRoleRepository;
import com.jds.mc.infrastucture.jpa.PermissionRepository;
import com.jds.mc.infrastucture.jpa.UserRepository;
import com.jds.mc.infrastucture.mapper.PermissionMapper;
import com.jds.mc.infrastucture.mapper.UserMapper;
import com.jds.mc.infrastucture.utils.RedisUtils;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author jensen_deng
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthImpl implements IAuth {

  private final UserRepository userRepository;

  private final UserRoleRepository userRoleRepository;

  private final PermissionRepository permissionRepository;

  private final CacheManager cacheManager;

  private final RedisUtils redisUtils;

  @Override
  public void login(User domain) {
    Optional<User> userInfo =
        userRepository
            .findOne(Example.of(UserMapper.INSTANCE.from(domain)))
            .map(UserMapper.INSTANCE::toDomain);
    Long userId =
        userInfo
            .map(
                x -> {
                  // 保存用户缓存
                  saveSession(x);
                  return x.getId();
                })
            .orElseThrow(() -> new IllegalArgumentException("account or password incorrect."));

    List<Permission> permissions =
        userInfo
            .map(
                u ->
                    userRoleRepository
                        .findOne(Example.of(UserRolePO.builder().userId(u.getId()).build()))
                        .map(
                            y ->
                                permissionRepository
                                    .findAll(
                                        Example.<PermissionPO>of(
                                            PermissionPO.builder().id(y.getRoleId()).build()))
                                    .stream()
                                    .map(PermissionMapper.INSTANCE::toDomain)
                                    .toList())
                        .orElse(Collections.emptyList()))
            .orElse(Collections.emptyList());

    // 保存权限
    savePermissions(userId, permissions);
  }

  private void saveSession(User domain) {
    redisUtils.set(String.valueOf(domain.getId()), domain);
  }

  // 缓存用户权限到Redis
  public void savePermissions(Long id, List<Permission> permissions) {
    //      String key = String.format("login:permission:%s", userName);
    //
    //      HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
    //      hashOperations.putAll(key, permissions.stream().collect(
    //              Collectors.toMap(p -> p.getMethod().concat(":").concat(p.getUri()),
    //                      Permission::getName, (k1, k2) -> k2)));
    //
    //      if (expireTime != null) {
    //        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    //      }
    //    }
  }
}
