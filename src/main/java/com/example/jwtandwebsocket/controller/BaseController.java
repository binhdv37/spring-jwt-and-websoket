package com.example.jwtandwebsocket.controller;

import com.example.jwtandwebsocket.common.constant.CommonRespMessage;
import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.common.model.BaseResponse;
import com.example.jwtandwebsocket.service.role.RoleService;
import com.example.jwtandwebsocket.service.security.model.SecurityUser;
import com.example.jwtandwebsocket.service.user.UserService;
import com.example.jwtandwebsocket.utils.exceptionHandler.MyExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@Slf4j
public abstract class BaseController {

    @Autowired
    private MyExceptionHandler myExceptionHandler;

    @Autowired
    protected UserService userService;

    @Autowired
    protected RoleService roleService;

    @ExceptionHandler(Exception.class)
    public void handle(Exception ex, HttpServletResponse response) {
        myExceptionHandler.handle(ex, response);
    }

    protected SecurityUser getCurrentUser() throws MyAppException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser) {
            return (SecurityUser) authentication.getPrincipal();
        } else {
            throw new MyAppException(CommonRespMessage.NOT_AUTHORIZATION, RespCode.AUTHENTICATION);
        }
    }

    protected <T> T checkNotNull(T t) throws MyAppException {
        if (t == null) {
            throw new MyAppException(CommonRespMessage.REQ_ITEM_NOT_FOUND, RespCode.ITEM_NOT_FOUND);
        }
        return t;
    }

    protected <T> BaseResponse<T> toBaseResponse(T data) throws MyAppException {
        return new BaseResponse<>(RespCode.SUCCESS.value(), CommonRespMessage.SUCCESS, HttpStatus.OK.value(), data);
    }

    protected <T> BaseResponse<T> checkNullAndToBaseResp(T data) throws MyAppException {
        checkNotNull(data);
        return new BaseResponse<>(RespCode.SUCCESS.value(), CommonRespMessage.SUCCESS, HttpStatus.OK.value(), data);
    }

}
