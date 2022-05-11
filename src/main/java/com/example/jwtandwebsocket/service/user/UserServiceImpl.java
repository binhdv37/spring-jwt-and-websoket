package com.example.jwtandwebsocket.service.user;

import com.example.jwtandwebsocket.dao.user.UserDao;
import com.example.jwtandwebsocket.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto findById(UUID id) {
        return userDao.findById(id);
    }
}
