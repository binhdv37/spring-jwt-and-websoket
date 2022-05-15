package com.example.jwtandwebsocket.utils.token;

import com.example.jwtandwebsocket.common.constant.AuthorityConstant;
import com.example.jwtandwebsocket.config.JwtSettings;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
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
        claims.put(SCOPES, Collections.singletonList(AuthorityConstant.REFRESH_TOKEN));
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

    public SecurityUser parseAccessJwtToken(String rawToken) {
        Jws<Claims> jwsClaims = parseTokenClaims(rawToken);
        Claims claims = jwsClaims.getBody();
        String subject = claims.getSubject();
        @SuppressWarnings("unchecked")
        List<String> scopes = claims.get(SCOPES, List.class);
        if (scopes == null || scopes.isEmpty()) {
            throw new IllegalArgumentException("JWT Token doesn't have any scopes");
        }

        SecurityUser securityUser = new SecurityUser();
        securityUser.setId(UUID.fromString(claims.get(USER_ID, String.class)));
        securityUser.setUsername(claims.get(USER_NAME, String.class));
        securityUser.setFullName(claims.get(FULL_NAME, String.class));
        securityUser.setEmail(subject);
        securityUser.setPhoneNumber(claims.get(PHONE_NUMBER, String.class));
        securityUser.setEnable(claims.get(ENABLE, Boolean.class));
        securityUser.setAuthorities(scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return securityUser;
    }

    public Jws<Claims> parseTokenClaims(String rawToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSettings.getSigningKey())
                    .parseClaimsJws(rawToken);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            log.debug("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            log.debug("JWT Token is expired", expiredEx);
            throw new AuthenticationException("JWT Token expired", expiredEx) {
            };
        }
    }

}
