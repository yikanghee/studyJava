package spring.session1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
public class Session1Application {

    public static void main(String[] args) {
        SpringApplication.run(Session1Application.class, args);
    }

}
