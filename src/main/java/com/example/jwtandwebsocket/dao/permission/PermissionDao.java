package com.example.jwtandwebsocket.dao.permission;

import com.example.jwtandwebsocket.dao.Dao;
import com.example.jwtandwebsocket.dto.permission.PermissionDto;

import java.util.List;
import java.util.UUID;

public interface PermissionDao extends Dao<PermissionDto> {

    List<PermissionDto> findAllByRoleId(UUID roleId);

}
