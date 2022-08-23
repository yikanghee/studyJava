package hello.springmvc.controller;

import hello.springmvc.entity.UserRepository;
import hello.springmvc.entity.Users;
import hello.springmvc.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/member", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postLogin(@RequestBody Map<String, Object> requestBody) {

        String userId = requestBody.get("userId").toString();
        String password = requestBody.get("password").toString();

        userService.postLogin(userId, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postRegister(@RequestBody Map<String, Object> requestBody) {

        String email = requestBody.get("email").toString();
        String userId = requestBody.get("userId").toString();
        String password = requestBody.get("password").toString();

        userService.postRegister(email, userId, password);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
