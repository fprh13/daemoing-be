package com.daemoing.daemo.dto;

import com.daemoing.daemo.domain.Univ;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDto {

    /**
     * create
     */
    @Data
    @AllArgsConstructor
    public static class SaveDto {
        private String parentCategory;
        private String childCategory;
    }


    /**
     * update 기존 카테고리 명을 변경
     */
    @Data
    @AllArgsConstructor
    public static class UpdateDto {
        private String parentCategory;
        private String childCategory;
    }

    /**
     * delete
     */
    @Data
    @AllArgsConstructor
    public static class DeleteDto {
        private String parentCategory;
        private String childCategory;
    }

    /**
     * read page
     */
    @Data
    public static class ClubListDto {
        private String name; // 모임 이름
        private int viewCount;
        private LocalDateTime activeDate; // 모임 활동 날짜
        private boolean isOnline; // 온라인 / 오프 라인
        private int applicantCount; // 신청자 수
        private int participantCount; // 참가자 수
        private int participantMax; // 최대 인원
        private Univ univ; // 모임 대학 정보

        @QueryProjection
        public ClubListDto(String name,
                           int viewCount,
                           LocalDateTime activeDate,
                           boolean isOnline, int applicantCount,
                           int participantCount,
                           int participantMax,
                           Univ univ) {
            this.name = name;
            this.viewCount = viewCount;
            this.activeDate = activeDate;
            this.isOnline = isOnline;
            this.applicantCount = applicantCount;
            this.participantCount = participantCount;
            this.participantMax = participantMax;
            this.univ = univ;
        }
    }


    /**
     * read request
     */
    @Data
    @AllArgsConstructor
    public static class ReadReqDto {
        private String univ;
        private String city;
        private String parentCategory;
        private String childCategory;
        private Boolean isOnline; // null 일 수 있으니
        private String keyword;
        // 페이지 정렬 조건
        private Integer page; // null 일 수 있으니
        private String order;
    }
}
