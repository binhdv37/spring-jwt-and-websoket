package com.example.jwtandwebsocket.service.permission;

import com.example.jwtandwebsocket.dao.permission.PermissionDao;
import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public List<PermissionDto> findAllByRoleId(UUID roleId) {
        return permissionDao.findAllByRoleId(roleId);
    }

}
