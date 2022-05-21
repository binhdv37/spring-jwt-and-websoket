package com.example.jwtandwebsocket.repo.roleAndPermission;

import com.example.jwtandwebsocket.entity.role_and_permission.RoleAndPermissionEntity;
import com.example.jwtandwebsocket.entity.role_and_permission.RoleAndPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleAndPermissionRepository extends JpaRepository<RoleAndPermissionEntity, RoleAndPermissionId> {

    void deleteAllByRoleId(UUID roleId);

    boolean existsByRoleId(UUID roleId);

}
