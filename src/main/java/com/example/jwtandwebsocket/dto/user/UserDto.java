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

    public UserDto(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.fullName = userDto.getFullName();
        this.phoneNumber = userDto.getPhoneNumber();
        this.enable = userDto.isEnable();
        this.createdTime = userDto.getCreatedTime();
        this.createdBy = userDto.getCreatedBy();
        this.updatedTime = userDto.getUpdatedTime();
        this.updatedBy = userDto.getUpdatedBy();
        this.roleDto = userDto.getRoleDto();
    }
}
