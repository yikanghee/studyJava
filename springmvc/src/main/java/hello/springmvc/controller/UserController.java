package hello.springmvc.controller;

import hello.springmvc.entity.UserRepository;
import hello.springmvc.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "member", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> postLogin(@RequestBody Map<String, Object> requestBody) {

        Users users = Users.builder().userId(requestBody.get("userId").toString())
                .password(requestBody.get("password").toString())
                .build();

        userRepository.save(users);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
