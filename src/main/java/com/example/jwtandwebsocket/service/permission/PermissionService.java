package com.example.jwtandwebsocket.service.permission;

import com.example.jwtandwebsocket.dto.permission.PermissionDto;

import java.util.List;
import java.util.UUID;

public interface PermissionService {

    List<PermissionDto> findAllByRoleId(UUID roleId);

}
