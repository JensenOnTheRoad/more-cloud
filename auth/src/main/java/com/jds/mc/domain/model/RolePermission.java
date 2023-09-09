package com.jds.mc.domain.model;

import lombok.*;

/**
 * @author jensen_deng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermission extends BaseModel {

  private Long roleId;

  private Long permissionId;
}
