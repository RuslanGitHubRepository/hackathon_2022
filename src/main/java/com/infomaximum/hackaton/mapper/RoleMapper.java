package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.role.Role;
import com.infomaximum.hackaton.model.role.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleDtoToRole(RoleDto roleDto);
    RoleDto roleToRoleDto(Role role);
}
