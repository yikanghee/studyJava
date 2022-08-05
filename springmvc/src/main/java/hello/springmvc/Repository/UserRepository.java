package hello.springmvc.Repository;

import hello.springmvc.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static Map<Long, User> userMap = new HashMap<>();
    private static long sequence = 1L;

    public User save(User user) {

    }
}
