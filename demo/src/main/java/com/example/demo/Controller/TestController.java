package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testApi() {
        return "React 안녕";
    }
}
