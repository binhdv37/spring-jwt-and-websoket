package com.example.jwtandwebsocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

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
}
