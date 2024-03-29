package com.daemoing.daemo.domain;

import com.daemoing.daemo.domain.type.Gender;
import com.daemoing.daemo.domain.type.Role;
import com.daemoing.daemo.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String loginId; // Principal

    private String password; // Credential

    private String username;

    private String description;

    private String email; // 비밀번호 찾기

    private int studentId; // 학벅 (19학번)

    @Enumerated(EnumType.STRING) // 성별
    private Gender gender;

    @Embedded
    private Univ univ; // 대학 정보

    @Enumerated(EnumType.STRING)
    private Role role; // 사용자 권한

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Board> boards = new ArrayList<>(); //board

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<UserClub> userClubs = new ArrayList<>(); // userClub

    public User(String loginId, String password, String username, String description, String email, int studentId, Gender gender, Univ univ, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.description = description;
        this.email = email;
        this.studentId = studentId;
        this.gender = gender;
        this.univ = univ;
        this.role = role;
    }

    //==변경 로직==//
    public void update(String loginId, String username, String description ,String email, int studentId ,Gender gender,Univ univ) {
        this.loginId = loginId;
        this.username = username;
        this.description = description;
        this.email = email;
        this.studentId = studentId;
        this.gender = gender;
        this.univ = univ;
    }



}

