package RestAPI.EX.cc;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {

    private static int usersCount = 3;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "anaa", new Date()));
        users.add(new User(2, "one", new Date()));
        users.add(new User(3, "joy", new Date()));
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return users;
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
