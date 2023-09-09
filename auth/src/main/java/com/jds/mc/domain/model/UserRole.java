package com.jds.mc.domain.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole extends BaseModel {

  private Long userId;

  private Long roleId;

  // Getter and Setter methods
}
