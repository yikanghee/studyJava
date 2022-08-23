package hello.springmvc.service.serviceImpl;

import hello.springmvc.entity.UserRepository;
import hello.springmvc.entity.Users;
import hello.springmvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void postLogin(String userId, String password) {

        Users user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new IllegalArgumentException("등록된 회원이 아닙니다.");
        }
    }

    @Override
    public void postRegister(String email, String userId, String password) {

        Users user = userRepository.findByUserId(userId);

        if (user != null) {
            throw new IllegalArgumentException("이미 등록된 회원입니다");
        }

        Users users = Users.builder()
                .userId(userId)
                .email(email)
                .password(password)
                .build();

        userRepository.save(users);
    }



}
