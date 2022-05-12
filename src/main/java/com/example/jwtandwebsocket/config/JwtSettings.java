package com.example.jwtandwebsocket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtSettings {

    private String signingKey;

    private String tokenIssuer;

    private int tokenExpTime;

    private int refreshTokenExpTime;

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public int getTokenExpTime() {
        return tokenExpTime;
    }

    public void setTokenExpTime(int tokenExpTime) {
        this.tokenExpTime = tokenExpTime;
    }

    public int getRefreshTokenExpTime() {
        return refreshTokenExpTime;
    }

    public void setRefreshTokenExpTime(int refreshTokenExpTime) {
        this.refreshTokenExpTime = refreshTokenExpTime;
    }
}
