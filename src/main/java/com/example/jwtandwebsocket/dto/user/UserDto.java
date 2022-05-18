package com.example.jwtandwebsocket.dto.user;

import com.example.jwtandwebsocket.dto.BaseDto;
import com.example.jwtandwebsocket.dto.role.RoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements BaseDto {
    private UUID id; // can not update

    @NotBlank(message = "username can not be blank")
    private String username; // can not update
    @JsonIgnore
    private String password;

    @NotBlank(message = "email can not be blank")
    private String email; // can not update

    @NotBlank(message = "fullName can not be blank")
    private String fullName;

    private String phoneNumber;

    private boolean enable;

    private Long createdTime;
    private UUID createdBy;
    private Long updatedTime;
    private UUID updatedBy;
    private RoleDto roleDto;
    private UUID roleId; // custom

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
        this.roleId = userDto.getRoleId();
    }
}
