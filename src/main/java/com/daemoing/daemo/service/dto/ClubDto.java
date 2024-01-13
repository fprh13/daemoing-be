package com.daemoing.daemo.service.dto;

import com.daemoing.daemo.domain.Univ;
import com.daemoing.daemo.domain.UserClub;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public class ClubDto {


    /**
     * create
     */
    @Data
    @AllArgsConstructor
    public static class SaveDto {
        private String name; // 모임 이름
        private String description; // 모임 설명
        private String openChatAddress; // 오픈 채팅 주소
        private LocalDateTime activeDate; // 모임 활동 날짜
        private boolean isOnline; // 온라인 / 오프 라인
        private int participantMax; // 최대 인원
        private Univ univ; // 모임 대학 정보

        // 그룹 카테고리
        private String parentCategory;
        private String childCategory;
    }

    /**
     * read one
     */
    // TODO: 카테고리도 포함 할 수 있도록 수정
    @Data
    @AllArgsConstructor
    public static class DetailDto {
        private String name; // 모임 이름
        private String description; // 모임 설명
        private String openChatAddress; // 오픈 채팅 주소
        private LocalDateTime activeDate; // 모임 활동 날짜
        private boolean isOnline; // 온라인 / 오프 라인
        private int participantMax; // 최대 인원
        private Univ univ; // 모임 대학 정보
    }

    /**
     * update
     */
    @Data
    @AllArgsConstructor
    public static class UpdateDto {
        private String name; // 모임 이름
        private String description; // 모임 설명
        private String openChatAddress; // 오픈 채팅 주소
        private LocalDateTime activeDate; // 모임 활동 날짜
        private boolean isOnline; // 온라인 / 오프 라인
        private int participantMax; // 최대 인원
        private Univ univ; // 모임 대학 정보

        // 그룹 카테고리
        private String parentCategory;
        private String childCategory;
    }

    /**
     * read page response
     */
    @Data
    @AllArgsConstructor
    public static class PageResDto {
        private String name;
        private LocalDateTime activeDate; // 모임 활동 날짜
        private int participantMax; // 최대 인원
        private int applicantCount; // 신청자 수
        private int participantCount; // 현재 참가자 수
        private Univ univ; // 모임 대학 정보
        private boolean isOnline; // 온라인 / 오프 라인
    }

    /**
     * read page request
     */
    @Data
    @AllArgsConstructor
    public static class pageReqDto {
        private String univ;
        private String location;
        private String parentCategory;
        private String childCategory;
        private boolean isOnline; // 온라인 / 오프 라인
        private boolean recent;
        private boolean mostView;
        private boolean trending;// applicantCount 신청자 수 많은 순
    }

    /**
     * read 신청자 상태 목록
     */
    @Data
    public static class ApplicantListDto {
        private String applicant;
        private String clubAccessState;

        public ApplicantListDto(UserClub userClub) {
            this.applicant = userClub.getApplicant();
            this.clubAccessState = userClub.getClubAccessState().toString();
        }
    }
}
