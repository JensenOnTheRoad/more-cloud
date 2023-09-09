package com.jds.mc.infrastucture.mapper;

import com.jds.mc.domain.model.Permission;
import com.jds.mc.infrastucture.db.PermissionPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jensen
 */
@Mapper
public interface PermissionMapper {
  PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

  Permission toDomain(PermissionPO obj);

  PermissionPO from(Permission obj);
}
