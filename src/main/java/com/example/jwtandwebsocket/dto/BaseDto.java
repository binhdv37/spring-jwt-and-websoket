package com.example.jwtandwebsocket.dto;

import java.util.UUID;

public interface BaseDto {

    UUID getId();

    void setId(UUID id);

    Long getCreatedTime();

    void setCreatedTime(Long createdTime);

}
