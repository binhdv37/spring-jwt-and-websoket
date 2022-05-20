package com.example.jwtandwebsocket.dao.permission;

import com.example.jwtandwebsocket.dao.AbstractJpaDao;
import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import com.example.jwtandwebsocket.entity.permission.PermissionEntity;
import com.example.jwtandwebsocket.repo.permission.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PermissionDaoImpl extends AbstractJpaDao<PermissionEntity, PermissionDto> implements PermissionDao{

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionDaoImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected Class<PermissionEntity> getEntityClass() {
        return PermissionEntity.class;
    }

    @Override
    protected CrudRepository<PermissionEntity, UUID> getCrudRepository() {
        return permissionRepository;
    }

    @Override
    public List<PermissionDto> findAllByRoleId(UUID roleId) {
        List<PermissionEntity> permissionEntities = permissionRepository.findAllByRoleId(roleId);
        return permissionEntities.stream()
                .map(PermissionEntity::toData)
                .collect(Collectors.toList());
    }
}
