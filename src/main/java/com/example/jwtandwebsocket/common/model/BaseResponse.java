package com.example.jwtandwebsocket.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private int respCode; // resp code defined in RespCode enum
    private String respMessage; // resp message
    private int statusCode; // http status code: 200, 400, ...
    private T data;
}
