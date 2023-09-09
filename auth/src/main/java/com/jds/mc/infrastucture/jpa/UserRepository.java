package com.jds.mc.infrastucture.jpa;

import com.jds.mc.infrastucture.db.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserPO, Long> {}
