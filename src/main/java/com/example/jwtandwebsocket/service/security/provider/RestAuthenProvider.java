package com.example.jwtandwebsocket.service.security.provider;

import com.example.jwtandwebsocket.dto.permission.PermissionDto;
import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.service.permission.PermissionService;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import com.example.jwtandwebsocket.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestAuthenProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final PermissionService permissionService;

    @Autowired
    public RestAuthenProvider(
            PasswordEncoder passwordEncoder,
            UserService userService, PermissionService permissionService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = (String) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();
            UserDto userDto = userService.findByUsername(username);
            if (userDto == null) {
                throw new UsernameNotFoundException("Authentication failed. Username not found");
            }
            boolean validPassword = passwordEncoder.matches(password, userDto.getPassword());
            if (!validPassword) {
                throw new BadCredentialsException("Authentication failed. Invalid password");
            }

            Collection<GrantedAuthority> authorities = null;
            if (userDto.getRoleId() != null) {
                List<PermissionDto> permissionDtoList = permissionService.findAllByRoleId(userDto.getRoleId());
                if (permissionDtoList != null && permissionDtoList.size() > 0) {
                    authorities = permissionDtoList.stream()
                            .map(p -> new SimpleGrantedAuthority(p.getKey()))
                            .collect(Collectors.toList());
                }
            }

            SecurityUser securityUser = new SecurityUser(userDto, authorities);
            return new UsernamePasswordAuthenticationToken(securityUser, null, authorities);
        } catch (Exception e) {
            throw new BadCredentialsException("Authentication failed. Bad user credential.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication
                .equals(UsernamePasswordAuthenticationToken.class); // provider nay duoc dung voi authentication la UsernamePasswordAuthenticationToken
    }

}
