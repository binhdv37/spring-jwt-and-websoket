package com.example.jwtandwebsocket.controller;

import com.example.jwtandwebsocket.common.constant.AuthorityConstant;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_VIEW + "\")")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID id) throws MyAppException {
        return ResponseEntity.ok(checkNullAndToBaseResp(userService.findById(id)));
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_VIEW + "\")")
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllUser() throws MyAppException {
        return ResponseEntity.ok(checkNullAndToBaseResp(userService.findAll()));
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_CREATE + "\", \"" + AuthorityConstant.USER_CREATE + "\")")
    @PostMapping(value = "")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) throws MyAppException {
        SecurityUser securityUser = getCurrentUser();
        return ResponseEntity.ok(checkNullAndToBaseResp(userService.save(userDto, securityUser.getId())));
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_DELETE + "\")")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) throws MyAppException {
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
