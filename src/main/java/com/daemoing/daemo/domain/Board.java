package com.daemoing.daemo.domain;

import com.daemoing.daemo.global.auditing.BaseCreateByEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
@AllArgsConstructor
@Getter
public class Board extends BaseCreateByEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title; // 글 제목

    private String content; // 글 내용

    private int viewCount; // 조회수

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        user.getBoards().add(this); //연관관계 N
    }

    //==업데이트 로직==//
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //== 조회수 증가 ==//
    public void increaseView(int viewCount) {
        this.viewCount = viewCount + 1;
    }
}
