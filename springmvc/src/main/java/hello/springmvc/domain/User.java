package hello.springmvc.domain;

public class User {

    private String email;
    private String password;

    // default 생성자
    public User() {

    }

    // 생성자
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
