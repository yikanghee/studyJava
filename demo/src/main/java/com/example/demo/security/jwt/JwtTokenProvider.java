package com.example.demo.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {

    private final MyUserDetailsService myUserDetailsService;

    private final String secretKey;
    private final long tokenValidityInMs;
    private final long refreshTokenValidityInMs;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.token-validity-in-sec}") long tokenValidity,
                            @Value("${jwt.refresh-token-validity-in-sec}") long refreshTokenValidity,
                            MyUserDetailsService myUserDetailsService){
        this.secretKey = secretKey;
        this.tokenValidityInMs = tokenValidity * 1000;
        this.refreshTokenValidityInMs = refreshTokenValidity * 1000;
        this.myUserDetailsService = myUserDetailsService;
    }


    private Key key;


    @Override
    public void afterPropertiesSet() throws Exception {
        String encodeKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodeKey.getBytes());
    }

    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * 토큰으로 부터 Authentication 객체를 얻어온다
     * Authentication 안에 User 정보가 담겨있다
     * UsernamePasswordAuthenticationToken 객체로 Authentication을 쉽게 만들수있다
     * 매개변수로 UserDetails, pw, authorities 가지 넣어주면
     * setAuthenticated(true)로 인스턴스를 생성해주고
     * Spring-Security는 그것을 체크해서 로그인을 처리한다
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
    }

    public String createRefreshToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }
}
