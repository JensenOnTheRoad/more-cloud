package com.jds.mc.infrastucture.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author jensen_deng
 */
@Entity
@Table(name = "role_permission")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RolePermissionPO extends BasePO {
  private Long roleId;

  private Long permissionId;
}
