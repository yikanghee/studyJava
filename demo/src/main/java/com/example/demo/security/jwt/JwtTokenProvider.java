package com.example.demo.security.jwt;

import javafx.fxml.Initializable;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {

    private final MyUserDetailsService myUserDetailsService;

    private final String secretKey;
    private final long tokenValidityInMs;
    private final long refreshTokenValidityInMs;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey))
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
