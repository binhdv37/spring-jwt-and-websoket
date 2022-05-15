package com.example.jwtandwebsocket.service.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private SecurityUser securityUser;
    private String rawToken;

    public JwtAuthenticationToken(SecurityUser securityUser) {
        super(securityUser.getAuthorities());
        this.securityUser = securityUser;
        this.rawToken = null;
        this.setAuthenticated(true);
    }

    public JwtAuthenticationToken(String rawToken) {
        super(null);
        this.securityUser = null;
        this.rawToken = rawToken;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return rawToken;
    }

    @Override
    public Object getPrincipal() {
        return securityUser; // this is important, for getCurrentUser() function
    }
}
