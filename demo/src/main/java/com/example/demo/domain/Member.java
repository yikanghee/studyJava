package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entity -> 테이블과의 매핑, JPA가 관리
 * Table -> Entity와 매핑할 테이블을 지정
 * Getter -> 객체의 일관성을 위해서 Setter 지양, 객체 생성 시점에서 값을 넣어주기 위해 Builder를 사용
 * NoArgsConstructor -> @NoArgsConstructor(access = AccessLevel.PROTECTED)로 생성된 생성자
 *                      protected User() {}
 *                      생성자를 생성 후 @Builder
 */
@Entity
@Getter
@Table(name = "MEMBERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;


    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Builder
    public Member(String email, String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = Collections.singletonList(Role.ROLE_MEMBER);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
