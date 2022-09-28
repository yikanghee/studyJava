package spring.session2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class Session2Application {

    public static void main(String[] args) {
        SpringApplication.run(Session2Application.class, args);
    }

}
