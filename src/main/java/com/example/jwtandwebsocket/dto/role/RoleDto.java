package com.example.jwtandwebsocket.dto.role;

import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import com.example.jwtandwebsocket.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;
    private Long createdTime;
    private UUID createdBy;
    private Long updatedTime;
    private UUID updatedBy;
    private List<UserDto> userDtos;
    private List<PermissionDto> permissionDtos;
}
