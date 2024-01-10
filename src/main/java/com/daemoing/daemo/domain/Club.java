package com.daemoing.daemo.domain;


import com.daemoing.daemo.global.auditing.BaseCreateByEntity;
import com.daemoing.daemo.global.common.ErrorCode;
import com.daemoing.daemo.global.common.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "club")
@Builder
@AllArgsConstructor
@Getter
public class Club extends BaseCreateByEntity {

    @Id
    @GeneratedValue
    @Column(name = "club_id")
    private Long id;

    @Column(name = "club_name")
    private String name; // 모임 이름

    private String description; // 모임 설명

    private String openChatAddress; // 오픈 채팅 주소

    private LocalDateTime activeDate; // 모임 활동 날짜

    private boolean isOnline; // 온라인 / 오프 라인

    private int applicantCount; // 신청자 수

    private int participantMax; // 최대 인원

    private int participantCount; // 현재 참가자 수

    private int viewCount; // 모임 조회 수

    @Embedded
    private Univ univ; // 모임 대학 정보

    // TODO: 모임 썸 내일 관련 정보
    // TODO: 카테고리 관련 정보
    // TODO: 연관관계 관련 정보 (신청 방식이라면 MM관계 로 풀고 isApproval 값 넣는 것 고려)
    // TODO: 실시간 채팅 일 경우 신청 방식 X applicant, openChatAddress 삭제

    //== 업데이트 로직 ==//
    public void update(String name,
                       String description,
                       String openChatAddress,
                       boolean isOnline,
                       LocalDateTime activeDate,
                       int participantMax,
                       Univ univ) {
        this.name = name;
        this.description = description;
        this.openChatAddress = openChatAddress;
        this.isOnline = isOnline;
        this.activeDate = activeDate;
        this.participantMax = participantMax;
        this.univ = univ;
    }

    //== 조회수 증가 ==//
    public void increaseView(int viewCount) {
        this.viewCount = viewCount + 1;
    }

    //== 신청자 수 증가 ==//
    public void increaseApplicant() {
        this.applicantCount = applicantCount + 1;
    }

    //== 비지니스 로직==//
    public void overValidation() {
        if (this.participantCount < this.participantMax) {
            this.participantCount = participantCount + 1; // 참가 확정 + 1
        } else {
            throw new CustomException(ErrorCode.OVER_VALIDATION);
        }
    }
}
