package com.example.jwtandwebsocket.common.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonBean {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
