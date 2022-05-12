package com.example.jwtandwebsocket.common.exception;

import com.example.jwtandwebsocket.common.constant.RespCode;

public class MyAppException extends Exception {

    private RespCode respCode;

    public MyAppException(String message, RespCode respCode) {
        super(message);
        this.respCode = respCode;
    }

    public MyAppException(String message, Throwable cause, RespCode respCode) {
        super(message, cause);
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return respCode;
    }

}
