package com.example.jwtandwebsocket.service.role;

import com.example.jwtandwebsocket.dto.role.RoleDto;

import java.util.UUID;

public interface RoleService {

    RoleDto findById(UUID id);

}
