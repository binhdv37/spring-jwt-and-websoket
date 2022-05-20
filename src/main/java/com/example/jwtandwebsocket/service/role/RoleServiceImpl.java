package com.example.jwtandwebsocket.service.role;

import com.example.jwtandwebsocket.dao.role.RoleDao;
import com.example.jwtandwebsocket.dto.role.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleDto findById(UUID id) {
        return roleDao.findById(id);
    }

}
