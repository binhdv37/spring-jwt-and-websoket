package com.example.jwtandwebsocket.dao.user;

import com.example.jwtandwebsocket.dao.Dao;
import com.example.jwtandwebsocket.dto.user.UserDto;

public interface UserDao extends Dao<UserDto> {

    UserDto findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

}
