package com.jds.mc.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author jensen_deng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

  private String username;

  private String password;

  private String nickName;

  private String email;

  private String mobile;

  private Integer sex;

  private Boolean status;

  private String remark;
}
