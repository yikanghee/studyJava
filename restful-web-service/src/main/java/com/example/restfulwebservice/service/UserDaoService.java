package com.example.restfulwebservice.service;

import com.example.restfulwebservice.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {

    private static int userCount = 3;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "kk", new Date()));
        users.add(new User(2, "jj", new Date()));
        users.add(new User(3, "ff", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
