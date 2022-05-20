package com.example.jwtandwebsocket.repo.permission;

import com.example.jwtandwebsocket.entity.permission.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    @Query(value = "select pe " +
            "from PermissionEntity pe inner join RoleAndPermissionEntity rap " +
            "on pe.id = rap.permissionId " +
            "where rap.roleId = :roleId")
    List<PermissionEntity> findAllByRoleId(@Param("roleId") UUID roleId);
}
