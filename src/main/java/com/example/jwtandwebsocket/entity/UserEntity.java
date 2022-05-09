package com.example.jwtandwebsocket.entity;

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
public class UserEntity {

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
}
