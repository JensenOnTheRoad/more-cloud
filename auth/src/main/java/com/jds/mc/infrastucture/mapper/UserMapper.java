package com.jds.mc.infrastucture.mapper;

import com.jds.mc.domain.model.User;
import com.jds.mc.infrastucture.db.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jensen
 */
@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User toDomain(UserPO obj);

  UserPO from(User obj);
}
