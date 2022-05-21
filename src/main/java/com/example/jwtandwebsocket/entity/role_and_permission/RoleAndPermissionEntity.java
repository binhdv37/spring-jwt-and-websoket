package com.example.jwtandwebsocket.entity.role_and_permission;

import com.example.jwtandwebsocket.dto.roleAndPermission.RoleAndPermissionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role_and_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoleAndPermissionId.class)
public class RoleAndPermissionEntity {

    @Id
    @Column(name = "role_id")
    private UUID roleId;

    @Id
    @Column(name = "permission_id")
    private UUID permissionId;

    public RoleAndPermissionEntity(RoleAndPermissionDto dto) {
        this.roleId = dto.getRoleId();
        this.permissionId = dto.getPermissionId();
    }

    public RoleAndPermissionDto toData() {
        RoleAndPermissionDto result = new RoleAndPermissionDto();
        result.setRoleId(roleId);
        result.setPermissionId(permissionId);
        return result;
    }
}
