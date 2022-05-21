package com.example.jwtandwebsocket.dao.roleAndPermission;

import com.example.jwtandwebsocket.dto.roleAndPermission.RoleAndPermissionDto;
import com.example.jwtandwebsocket.entity.role_and_permission.RoleAndPermissionEntity;
import com.example.jwtandwebsocket.repo.roleAndPermission.RoleAndPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoleAndPermissionDaoImpl implements RoleAndPermisionDao {

    private final RoleAndPermissionRepository roleAndPermissionRepository;

    @Autowired
    public RoleAndPermissionDaoImpl(RoleAndPermissionRepository roleAndPermissionRepository) {
        this.roleAndPermissionRepository = roleAndPermissionRepository;
    }

    @Override
    public RoleAndPermissionDto save(RoleAndPermissionDto roleAndPermissionDto) {
        RoleAndPermissionEntity entity = new RoleAndPermissionEntity(roleAndPermissionDto);
        RoleAndPermissionEntity saved = roleAndPermissionRepository.save(entity);
        return entity.toData();
    }

    @Override
    public boolean deleteByRoleId(UUID roleId) {
        roleAndPermissionRepository.deleteAllByRoleId(roleId);
        return !roleAndPermissionRepository.existsByRoleId(roleId);
    }
}
