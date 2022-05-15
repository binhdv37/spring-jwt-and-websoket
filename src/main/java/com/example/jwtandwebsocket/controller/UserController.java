package com.example.jwtandwebsocket.controller;

import com.example.jwtandwebsocket.common.constant.AuthorityConstant;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_VIEW + "\")")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID id) throws MyAppException {
        return ResponseEntity.ok(checkNotNull(userService.findById(id)));
    }
}
