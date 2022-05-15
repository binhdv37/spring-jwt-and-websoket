package com.example.jwtandwebsocket.common.constant;

public enum RespCode {
    SUCCESS(10),
    AUTHENTICATION(11),
    BAD_REQUEST_PARAMS(12), // validate request param by anotation
    VALIDATION_FAIL(13), // validation custom
    INTERNAL(14),
    ITEM_NOT_FOUND(15);

    private int respCode;

    RespCode(int respCode) {
        this.respCode = respCode;
    }

    public int value() {
        return respCode;
    }
}
