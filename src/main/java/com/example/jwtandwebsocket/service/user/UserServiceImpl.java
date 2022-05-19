package com.example.jwtandwebsocket.service.user;

import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyValidationException;
import com.example.jwtandwebsocket.dao.user.UserDao;
import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.utils.validator.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto findById(UUID id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserDto> findAll() {
        return userDao.findAll();
    }

    @Override
    public UserDto findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserDto save(UserDto userDto, UUID actioner) {
        userValidator.validateSave(userDto);
        if (userDto.getId() != null) { // update
            UserDto current = userDao.findById(userDto.getId());
            if (current == null) {
               return null;
            }
            current.setFullName(userDto.getFullName());
            current.setEnable(userDto.isEnable());
            current.setRoleDto(userDto.getRoleDto());
            current.setPhoneNumber(userDto.getPhoneNumber());
            current.setUpdatedTime(System.currentTimeMillis());
            current.setUpdatedBy(actioner);
            return userDao.save(current);
        }
        // encrypt password in case create new account
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userDao.save(userDto);
    }

    @Override
    public boolean deleteById(UUID id) {
        return userDao.deleteById(id);
    }

    private final DataValidator<UserDto> userValidator = new DataValidator<UserDto>() {

        @Override
        public void validateSave(UserDto dto) {
            super.validateSave(dto);
        }

        @Override
        public void validateCreate(UserDto dto) {
            /*
               -  username exist
               -  password blank
               - email exist
               - phone number regex, exist
               - roleId exist
             */
            if (userDao.existsByUsername(dto.getUsername())) {
               throw new MyValidationException("username already exist", RespCode.VALIDATION_FAIL);
            }

            if (dto.getPassword() == null || dto.getPassword().trim().equals("")) {
               throw new MyValidationException("password can not be blank", RespCode.VALIDATION_FAIL);
            }

            if (userDao.existsByEmail(dto.getEmail())) {
                throw new MyValidationException("email already exist", RespCode.VALIDATION_FAIL);
            }

            if (dto.getPhoneNumber() != null) {
                if (!isValidPhone(dto.getPhoneNumber())) {
                    throw new MyValidationException("invalid phone number pattern", RespCode.VALIDATION_FAIL);
                }

                if (userDao.existsByPhoneNumber(dto.getPhoneNumber())) {
                    throw new MyValidationException("phone number already exist", RespCode.VALIDATION_FAIL);
                }
            }


        }

        @Override
        public void validateUpdate(UserDto dto) {
        }
    };
}
