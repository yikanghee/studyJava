package com.example.demo.Controller;

import com.example.demo.Service.LoginService;
import com.example.demo.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {


    private final LoginService loginService;

    @PostMapping("/api/v1/signUp")
    public Long signUp(@RequestParam String id,
                       @RequestParam String pw) {
        return loginService.signUp(id, pw);
    }

    @PostMapping("/api/v1/signIn")
    public TokenResponseDto signIn(@RequestParam String id,
                                   @RequestParam String pw) {
        return loginService.signIn(id, pw);
    }

    @PostMapping("/api/v1/accessToken")
    public TokenResponseDto reissueAccessToken(@RequestParam String token) {
        return loginService.reissueAccessToken(token);
    }

    @GetMapping("/auth/test")
    public String test(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("--/auth/test-- | userId : {}, Role : {}",
                userDetails.getUsername(), userDetails.getAuthorities());
        return "success auth";
    }
}
