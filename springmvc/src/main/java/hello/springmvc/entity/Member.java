package hello.springmvc.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static long id;

    private static String username;

    private static String email;

    private static String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
