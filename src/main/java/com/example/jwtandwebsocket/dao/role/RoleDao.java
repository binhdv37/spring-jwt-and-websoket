package com.example.jwtandwebsocket.dao.role;

import com.example.jwtandwebsocket.dao.Dao;
import com.example.jwtandwebsocket.dto.role.RoleDto;

import java.util.UUID;

public interface RoleDao extends Dao<RoleDto> {

    boolean existsByName(String name);

    boolean existsByName(String name, UUID id);
}
