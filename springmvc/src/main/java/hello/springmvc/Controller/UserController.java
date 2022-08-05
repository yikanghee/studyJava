package hello.springmvc.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<String> login() {

        log.info("성공했다 시봉세야");

        return ResponseEntity.status(200).body("성공했습니다");
        }
        }
