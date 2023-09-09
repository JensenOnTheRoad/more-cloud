package com.jds.mc.infrastucture.db;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author jensen_deng
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserPO extends BasePO {

  @Column(nullable = false, length = 64)
  private String username;

  @Column(nullable = false, length = 64)
  private String password;

  @Column(nullable = false, length = 64)
  private String nickName;

  @Column(length = 64, nullable = false)
  private String email;

  @Column(length = 32, nullable = false)
  private String mobile;

  @Column(name = "sex")
  private Integer sex;

  @Column(name = "status")
  private Boolean status;

  @Column(name = "remark", length = 500)
  private String remark;
}
