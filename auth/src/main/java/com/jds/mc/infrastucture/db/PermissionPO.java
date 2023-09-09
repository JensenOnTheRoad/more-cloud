package com.jds.mc.infrastucture.db;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "permissions")
@Entity
public class PermissionPO extends BasePO {

  @Column(nullable = false, length = 64)
  private String name;

  @Column(length = 200)
  private String path;

  @Column() private String type;

  @Column(length = 255)
  private String component;

  @Column(length = 1)
  private Boolean visible;

  @Column(length = 1)
  private Integer status;

  @Column(name = "perms")
  private String perms;

  @Column(length = 100, columnDefinition = "varchar(100) default '#'")
  private String icon;

  @Column(name = "remark", length = 500)
  private String remark;
}
