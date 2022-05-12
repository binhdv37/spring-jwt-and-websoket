package com.example.jwtandwebsocket.utils.token;

import com.example.jwtandwebsocket.common.constant.Authority;
import com.example.jwtandwebsocket.config.JwtSettings;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenFactory {

    private final JwtSettings jwtSettings;

    private static final String SCOPES = "scopes";
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String FULL_NAME = "fullName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String ENABLE = "enable";

    @Autowired
    public JwtTokenFactory(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    public String createAccessToken(SecurityUser securityUser) {
        String subject = securityUser.getUsername();
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put(SCOPES, securityUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        claims.put(USER_ID, securityUser.getId().toString());
        claims.put(USER_NAME, securityUser.getUsername());
        claims.put(FULL_NAME, securityUser.getFullName());
        claims.put(EMAIL, securityUser.getEmail());
        claims.put(PHONE_NUMBER, securityUser.getPhoneNumber());
        claims.put(ENABLE, securityUser.isEnable());

        ZonedDateTime currentTime = ZonedDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtSettings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(jwtSettings.getTokenExpTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSettings.getSigningKey())
                .compact();
        return token;
    }

    public String createRefreshToken(SecurityUser securityUser) {
        String subject = securityUser.getUsername();
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put(SCOPES, Collections.singletonList(Authority.REFRESH_TOKEN.name()));
        claims.put(USER_ID, securityUser.getId().toString());

        ZonedDateTime currentTime = ZonedDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtSettings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(currentTime.plusSeconds(jwtSettings.getRefreshTokenExpTime()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSettings.getSigningKey())
                .compact();
        return token;
    }

}
