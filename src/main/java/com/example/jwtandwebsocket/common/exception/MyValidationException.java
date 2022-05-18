package com.example.jwtandwebsocket.common.exception;

import com.example.jwtandwebsocket.common.constant.RespCode;

import javax.validation.ValidationException;

public class MyValidationException extends ValidationException {

    private RespCode respCode;

    public MyValidationException(String message, RespCode respCode) {
        super(message);
        this.respCode = respCode;
    }

    public MyValidationException(String message, Throwable cause, RespCode respCode) {
        super(message, cause);
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return respCode;
    }
}
