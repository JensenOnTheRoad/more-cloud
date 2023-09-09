package com.jds.mc.infrastucture.db;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role")
public class RolePO extends BasePO {

  @Column(nullable = false, length = 64)
  private String name;

  @Column(length = 100)
  private String roleKey;

  @Column(length = 1)
  private Integer status;

  @Column(length = 500)
  private String remark;

  // Getter and Setter methods
}
