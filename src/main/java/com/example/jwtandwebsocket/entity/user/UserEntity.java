package com.example.jwtandwebsocket.entity.user;

import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.entity.BaseEntity;
import com.example.jwtandwebsocket.entity.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "AppUser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements BaseEntity<UserDto> {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "enable", nullable = false)
    private boolean enable;

    @Column(name = "createdTime")
    private Long createdTime;

    @Column(name = "createdBy")
    private UUID createdBy;

    @Column(name = "updatedTime")
    private Long updatedTime;

    @Column(name = "updatedBy")
    private UUID updatedBy;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    private RoleEntity roleEntity;

    @Override
    public UUID getUuid() {
        return this.id;
    }

    @Override
    public void setUuid(UUID id) {
        this.id = id;
    }

    @Override
    public UserDto toData() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userDto.setFullName(fullName);
        userDto.setPhoneNumber(phoneNumber);
        userDto.setEnable(enable);
        userDto.setRoleDto(roleEntity == null ? null : roleEntity.toData());
        userDto.setCreatedTime(createdTime);
        userDto.setCreatedBy(createdBy);
        userDto.setUpdatedTime(updatedTime);
        userDto.setUpdatedBy(updatedBy);
        return userDto;
    }
}
