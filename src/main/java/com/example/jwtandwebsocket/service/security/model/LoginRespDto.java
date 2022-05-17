package com.example.jwtandwebsocket.service.security.model;

import lombok.Data;

@Data
public class LoginRespDto {
    private String token;
    private String refreshToken;
}
