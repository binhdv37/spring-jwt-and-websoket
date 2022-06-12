package com.example.jwtandwebsocket.utils.exceptionHandler;

import com.example.jwtandwebsocket.common.constant.RespCode;
import com.example.jwtandwebsocket.common.exception.MyAppException;
import com.example.jwtandwebsocket.common.model.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Map<RespCode, HttpStatus> respCodeToStatusMap = new HashMap<>();

    static {
        respCodeToStatusMap.put(RespCode.SUCCESS, HttpStatus.OK);
        respCodeToStatusMap.put(RespCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED);
        respCodeToStatusMap.put(RespCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST);
        respCodeToStatusMap.put(RespCode.VALIDATION_FAIL, HttpStatus.BAD_REQUEST);
        respCodeToStatusMap.put(RespCode.INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
        respCodeToStatusMap.put(RespCode.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public void handle(Exception ex, HttpServletResponse response) {
        try {
            BaseResponse apiResponse = new BaseResponse();
            if (ex instanceof MyAppException) {
                MyAppException myAppException = (MyAppException) ex;
                int respCode = myAppException.getRespCode().value();
                int statusCode = respCodeToStatus(myAppException.getRespCode()).value();
                String message = ex.getMessage();

                apiResponse.setRespCode(respCode);
                apiResponse.setStatusCode(statusCode);
                apiResponse.setRespMessage(message);
                response.setStatus(statusCode);
            } else {
                int respCode = RespCode.INTERNAL.value();
                int statusCode = respCodeToStatus(RespCode.INTERNAL).value();
                String message = ex.getMessage();

                apiResponse.setRespCode(respCode);
                apiResponse.setStatusCode(statusCode);
                apiResponse.setRespMessage(message);
                response.setStatus(statusCode);
            }
            objectMapper.writeValue(response.getWriter(), apiResponse);
        } catch (Exception e) {
            log.error("Cannot handle exception: ", ex);
        }
    }

    private HttpStatus respCodeToStatus(RespCode respCode) {
        return respCodeToStatusMap.get(respCode);
    }


}

