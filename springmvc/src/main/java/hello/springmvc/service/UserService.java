package hello.springmvc.service;

import org.springframework.stereotype.Service;

public interface UserService {

    void postLogin(String userId, String password);

    void postRegister(String email, String userId, String password);
}
