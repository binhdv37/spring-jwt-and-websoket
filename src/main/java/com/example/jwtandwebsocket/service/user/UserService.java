package com.example.jwtandwebsocket.service.user;

import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto findById(UUID id);

    List<UserDto> findAll();

    UserDto findByUsername(String username);

    UserDto save(UserDto userDto, UUID actioner);

    boolean deleteById(UUID id) throws MyAppException;

}
