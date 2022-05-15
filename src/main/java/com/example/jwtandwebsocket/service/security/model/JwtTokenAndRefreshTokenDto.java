package com.example.jwtandwebsocket.service.security.model;

import lombok.Data;

@Data
public class JwtTokenAndRefreshTokenDto {
    private String token;
    private String refreshToken;
}
