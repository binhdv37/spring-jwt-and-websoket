package com.example.jwtandwebsocket.entity;

import java.util.UUID;

public interface BaseEntity<T> extends ToData<T> { // T: dto

    UUID getUuid();

    void setUuid(UUID id);

    Long getCreatedTime();

    void setCreatedTime(Long createdTime);

}
