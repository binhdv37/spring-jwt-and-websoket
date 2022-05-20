package com.example.jwtandwebsocket.dao.role;

import com.example.jwtandwebsocket.dao.AbstractJpaDao;
import com.example.jwtandwebsocket.dto.role.RoleDto;
import com.example.jwtandwebsocket.entity.role.RoleEntity;
import com.example.jwtandwebsocket.repo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RoleDaoImpl extends AbstractJpaDao<RoleEntity, RoleDto> implements RoleDao {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDaoImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected Class<RoleEntity> getEntityClass() {
        return RoleEntity.class;
    }

    @Override
    protected CrudRepository<RoleEntity, UUID> getCrudRepository() {
        return roleRepository;
    }

}
