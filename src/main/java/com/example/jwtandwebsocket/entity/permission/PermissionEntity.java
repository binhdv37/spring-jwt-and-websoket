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
@Table(name = "Permission")
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

    @Override
    public UUID getUuid() {
        return this.id;
    }

    @Override
    public void setUuid(UUID id) {
        this.setId(id);
    }

    @Override
    public PermissionDto toData() {
        return null;
    }
}
