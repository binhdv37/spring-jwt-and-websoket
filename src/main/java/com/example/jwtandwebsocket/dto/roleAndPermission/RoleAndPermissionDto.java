package com.example.jwtandwebsocket.dto.roleAndPermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAndPermissionDto {
    private UUID roleId;
    private UUID permissionId;
}
