package com.example.jwtandwebsocket.dao.user;

import com.example.jwtandwebsocket.dao.AbstractJpaDao;
import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.entity.user.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public class UserDaoImpl extends AbstractJpaDao<UserEntity, UserDto> implements UserDao {

    @Override
    protected Class<UserEntity> getEntityClass() {
        return null;
    }

    @Override
    protected CrudRepository<UserEntity, UUID> getCrudRepository() {
        return null;
    }
}
