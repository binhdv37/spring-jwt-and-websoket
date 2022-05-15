package com.example.jwtandwebsocket.service.security.model;

import com.example.jwtandwebsocket.dto.user.UserDto;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class SecurityUser extends UserDto {
    private Collection<GrantedAuthority> authorities;

    public SecurityUser(UserDto userDto, Collection<GrantedAuthority> authorities) {
        super(userDto);
        this.authorities = authorities;
    }

    public SecurityUser() {
    }
}
