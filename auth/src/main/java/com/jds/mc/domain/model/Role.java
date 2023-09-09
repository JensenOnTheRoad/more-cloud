package com.jds.mc.domain.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends BaseModel {

  private String name;

  private String roleKey;

  private Integer status;

  private String remark;

  // Getter and Setter methods
}
