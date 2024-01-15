package com.daemoing.daemo.domain;

import com.daemoing.daemo.domain.type.ClubAccessState;
import com.daemoing.daemo.global.auditing.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserClub extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_club_id")
    private Long id;

    private String applicant; // 그룹 신청자

    @Enumerated(EnumType.STRING)
    private ClubAccessState clubAccessState; // 그룹 상태

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    public UserClub(String applicant, ClubAccessState clubAccessState) {
        this.applicant = applicant;
        this.clubAccessState = clubAccessState;
    }

    //==연관관계 메서드==//
    public void setClub(Club club) {
        this.club = club;
        club.getUserClubs().add(this);
    }
    public void setUser(User user) {
        this.user = user;
        user.getUserClubs().add(this);
    }

    //==그룹 신청==//
    public void apply(Club club, User user) {
        this.club = club;
        club.getUserClubs().add(this);
        this.user = user;
        user.getUserClubs().add(this);
    }

    //==신청 상태 변경==//
    public void update(ClubAccessState clubAccessState) {
        this.clubAccessState = clubAccessState;
    }

}
