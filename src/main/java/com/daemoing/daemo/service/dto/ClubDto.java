package com.daemoing.daemo.service.dto;

import com.daemoing.daemo.domain.Univ;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class ClubDto {


    /**
     * create
     */
    @Data
    @Builder
    @AllArgsConstructor
    public static class SaveDto {
        private String name; // 모임 이름
        private String description; // 모임 설명
        private String openChatAddress; // 오픈 채팅 주소
        private LocalDateTime activeDate; // 모임 활동 날짜
        private boolean isOnline; // 온라인 / 오프 라인
        private int participantMax; // 최대 인원
        private Univ univ; // 모임 대학 정보
    }
}
