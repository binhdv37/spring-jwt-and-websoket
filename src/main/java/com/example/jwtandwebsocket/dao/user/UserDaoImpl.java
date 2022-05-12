package com.example.jwtandwebsocket.dao.user;

import com.example.jwtandwebsocket.dao.AbstractJpaDao;
import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.entity.user.UserEntity;
import com.example.jwtandwebsocket.repo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDaoImpl extends AbstractJpaDao<UserEntity, UserDto> implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    protected CrudRepository<UserEntity, UUID> getCrudRepository() {
        return userRepository;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            return null;
        }
        return userEntity.toData();
    }
}
