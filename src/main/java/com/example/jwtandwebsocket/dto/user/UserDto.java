package com.example.jwtandwebsocket.dto.user;

import com.example.jwtandwebsocket.dto.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private boolean enable;
    private Long createdTime;
    private UUID createdBy;
    private Long updatedTime;
    private UUID updatedBy;
    private RoleDto roleDto;
}
