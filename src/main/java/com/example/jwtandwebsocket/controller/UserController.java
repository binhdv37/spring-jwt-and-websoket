package com.example.jwtandwebsocket.controller;

import com.example.jwtandwebsocket.common.constant.AuthorityConstant;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.dto.user.UserDto;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {

    @Operation(summary = "Get user object by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found user",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))
                    })
    })
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

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_CREATE + "\", \"" + AuthorityConstant.USER_UPDATE + "\")")
    @PostMapping(value = "")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) throws MyAppException {
        SecurityUser securityUser = getCurrentUser();
        return ResponseEntity.ok(checkNullAndToBaseResp(userService.save(userDto, securityUser.getId())));
    }

    @PreAuthorize("hasAnyAuthority(\"" + AuthorityConstant.USER_DELETE + "\")")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) throws MyAppException {
        return ResponseEntity.ok(checkNullAndToBaseResp(userService.deleteById(id)));
    }
}
