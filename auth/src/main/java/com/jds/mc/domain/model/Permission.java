package com.jds.mc.domain.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Permission extends BaseModel {

  private String name;

  private String path;

  private String type;

  private String component;

  private Boolean visible;

  @Desc("启用状态")
  private Integer status;

  private String perms;

  private String icon;

  private String remark;
}
