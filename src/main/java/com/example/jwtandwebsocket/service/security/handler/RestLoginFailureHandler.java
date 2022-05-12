package com.example.jwtandwebsocket.service.security.handler;

import com.example.jwtandwebsocket.utils.exceptionHandler.MyExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestLoginFailureHandler implements AuthenticationFailureHandler {

    private final MyExceptionHandler handler;

    @Autowired
    public RestLoginFailureHandler(MyExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        handler.handle(exception, response);
    }

}
