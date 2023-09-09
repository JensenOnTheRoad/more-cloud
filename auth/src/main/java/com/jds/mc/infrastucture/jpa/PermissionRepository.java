package com.jds.mc.infrastucture.jpa;

import com.jds.mc.infrastucture.db.PermissionPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionPO, Long> {}
