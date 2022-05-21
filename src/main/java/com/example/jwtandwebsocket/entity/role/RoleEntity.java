package com.example.jwtandwebsocket.entity.role;

import com.example.jwtandwebsocket.dto.role.RoleDto;
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
@Table(name = "role")
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

    public RoleEntity(RoleDto roleDto) {
        this.id = roleDto.getId();
        this.name = roleDto.getName();
        this.createdTime = roleDto.getCreatedTime();
        this.createdBy = roleDto.getCreatedBy();
        this.updatedTime = roleDto.getUpdatedTime();
        this.updatedBy = roleDto.getUpdatedBy();
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
    public RoleDto toData() {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(id);
        roleDto.setName(name);
        roleDto.setCreatedTime(createdTime);
        roleDto.setCreatedBy(createdBy);
        roleDto.setUpdatedTime(updatedTime);
        roleDto.setUpdatedBy(updatedBy);
        return roleDto;
    }
}
