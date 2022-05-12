package com.example.jwtandwebsocket.common.constant;

public enum RespCode {
    SUCCESS(10),
    BAD_REQUEST_PARAMS(11), // validate request param by anotation
    VALIDATION_FAIL(12), // validation custom
    INTERNAL(13);

    private int respCode;

    RespCode(int respCode) {
        this.respCode = respCode;
    }

    public int value() {
        return respCode;
    }
}
