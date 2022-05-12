package com.example.jwtandwebsocket.config;

import com.example.jwtandwebsocket.service.security.filter.RestLoginProcessingFilter;
import com.example.jwtandwebsocket.service.security.provider.RestAuthenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String REST_LOGIN_ENDPOINT = "/api/auth/login";
    private final String NONE_TOKEN_BASED_AUTH_ENTRY = "/api/noauth/**";
    private final String TOKEN_BASED_AUTH_ENTRY = "/api/**";

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Lazy private RestAuthenProvider restAuthenProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(restAuthenProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(REST_LOGIN_ENDPOINT).permitAll()
                .antMatchers(NONE_TOKEN_BASED_AUTH_ENTRY).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_BASED_AUTH_ENTRY).authenticated()
                .and()
                .addFilterBefore(buildRestLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    protected RestLoginProcessingFilter buildRestLoginProcessingFilter() throws Exception {
        RestLoginProcessingFilter filter = new RestLoginProcessingFilter(REST_LOGIN_ENDPOINT, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManagerBean());
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
