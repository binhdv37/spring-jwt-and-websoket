package com.example.jwtandwebsocket.dao.roleAndPermission;

import com.example.jwtandwebsocket.dto.roleAndPermission.RoleAndPermissionDto;

import java.util.UUID;

public interface RoleAndPermisionDao {

    RoleAndPermissionDto save(RoleAndPermissionDto roleAndPermissionDto);

    boolean deleteByRoleId(UUID roleId);

}
