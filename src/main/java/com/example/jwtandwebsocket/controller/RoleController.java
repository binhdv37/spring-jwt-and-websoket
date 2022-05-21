package com.example.jwtandwebsocket.controller;

import com.example.jwtandwebsocket.common.constant.AuthorityConstant;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.dto.role.RoleDto;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController extends BaseController {

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ROLE_VIEW + "\")")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable(value = "id") UUID roleId) throws MyAppException {
        return new ResponseEntity<>(checkNullAndToBaseResp(roleService.findById(roleId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ROLE_VIEW + "\")")
    @GetMapping("/all")
    public ResponseEntity<?> getAllRole() throws MyAppException {
        return new ResponseEntity<>(checkNullAndToBaseResp(roleService.findAll()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ROLE_CREATE + "\", \"" + AuthorityConstant.ROLE_UPDATE + "\")")
    @PostMapping
    public ResponseEntity<?> saveRole(@RequestBody RoleDto roleDto) throws MyAppException {
        SecurityUser securityUser = getCurrentUser();
        return new ResponseEntity<>(checkNullAndToBaseResp(roleService.save(roleDto, securityUser.getId())), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.ROLE_DELETE + "\")")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(value = "id") UUID roleId) throws MyAppException {
        return new ResponseEntity<>(checkNullAndToBaseResp(roleService.deleteById(roleId)), HttpStatus.OK);
    }

}
