package com.example.jwtandwebsocket.entity.role;

import com.example.jwtandwebsocket.dto.role.RoleDto;
import com.example.jwtandwebsocket.entity.BaseEntity;
import com.example.jwtandwebsocket.entity.permission.PermissionEntity;
import com.example.jwtandwebsocket.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements BaseEntity<RoleDto> {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "createdTime")
    private Long createdTime;

    @Column(name = "createdBy")
    private UUID createdBy;

    @Column(name = "updatedTime")
    private Long updatedTime;

    @Column(name = "updatedBy")
    private UUID updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roleEntity", cascade = CascadeType.ALL)
    private List<UserEntity> userEntities;

    @ManyToMany
    @JoinTable(
            name = "role_and_permision",
            joinColumns = @JoinColumn(name = "roleId"),
            inverseJoinColumns = @JoinColumn(name = "permissionId"),
            uniqueConstraints = @UniqueConstraint(name = "unique_role_and_permission", columnNames = {"roleId", "permissionId"})
    )
    private List<PermissionEntity> permissionEntities;

    @Override
    public UUID getUuid() {
        return this.id;
    }

    @Override
    public void setUuid(UUID id) {
        this.id = id;
    }

    @Override
    public RoleDto toData() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(id);
        roleDto.setName(name);
        roleDto.setUserDtos(userEntities == null
                ? null
                : userEntities.stream()
                .map(UserEntity::toData)
                .collect(Collectors.toList()));
        roleDto.setPermissionDtos(permissionEntities == null
                ? null
                : permissionEntities.stream()
                .map(PermissionEntity::toData)
                .collect(Collectors.toList()));
        roleDto.setCreatedTime(createdTime);
        roleDto.setCreatedBy(createdBy);
        roleDto.setUpdatedTime(updatedTime);
        roleDto.setUpdatedBy(updatedBy);
        return roleDto;
    }
}
