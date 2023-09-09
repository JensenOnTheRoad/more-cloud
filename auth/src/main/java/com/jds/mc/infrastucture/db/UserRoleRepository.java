package com.jds.mc.infrastucture.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRoleRepository
    extends JpaRepository<UserRolePO, Long>, JpaSpecificationExecutor<UserRolePO> {}
