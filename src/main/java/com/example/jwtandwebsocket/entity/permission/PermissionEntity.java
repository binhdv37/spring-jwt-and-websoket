package com.example.jwtandwebsocket.entity.permission;

import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import com.example.jwtandwebsocket.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity implements BaseEntity<PermissionDto> {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "createdTime", nullable = false)
    private Long createdTime;

    public PermissionEntity(PermissionDto dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.key = dto.getKey();
        this.createdTime = dto.getCreatedTime();
    }

    @Override
    public UUID getUuid() {
        return this.id;
    }

    @Override
    public void setUuid(UUID id) {
        this.id = id;
    }

    @Override
    public PermissionDto toData() {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(id);
        permissionDto.setKey(key);
        permissionDto.setName(name);
        return permissionDto;
    }
}
