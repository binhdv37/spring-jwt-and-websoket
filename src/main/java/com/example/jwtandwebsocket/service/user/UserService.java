package com.example.jwtandwebsocket.service.user;

import com.example.jwtandwebsocket.dto.user.UserDto;

import java.util.UUID;

public interface UserService {

    UserDto findById(UUID id);

}
