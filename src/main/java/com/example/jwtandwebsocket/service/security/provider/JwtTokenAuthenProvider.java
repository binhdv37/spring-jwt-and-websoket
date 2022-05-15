package com.example.jwtandwebsocket.service.security.provider;

import com.example.jwtandwebsocket.service.security.model.JwtAuthenticationToken;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import com.example.jwtandwebsocket.utils.token.JwtTokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthenProvider implements AuthenticationProvider {

    private final JwtTokenFactory tokenFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String rawToken = (String) authentication.getCredentials();
        SecurityUser securityUser = tokenFactory.parseAccessJwtToken(rawToken);

        // check token out date ?

        return new JwtAuthenticationToken(securityUser);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication
                .equals(JwtAuthenticationToken.class); // provider nay duoc dung voi authentication la JwtAuthenticationToken
    }

}
