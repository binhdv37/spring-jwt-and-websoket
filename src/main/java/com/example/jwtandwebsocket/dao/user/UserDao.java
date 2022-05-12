package com.example.jwtandwebsocket.dao.user;

import com.example.jwtandwebsocket.dao.Dao;
import com.example.jwtandwebsocket.dto.user.UserDto;

public interface UserDao extends Dao<UserDto> {

    UserDto findByUsername(String username);

}
