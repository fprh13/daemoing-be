package com.daemoing.daemo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

public class BoardDto {

    /**
     * create
     */
    @Data
    @AllArgsConstructor
    public static class WriteDto {
        private String title;
        private String content;
    }

    /**
     * update
     */
    @Data
    @AllArgsConstructor
    public static class UpdateDto {
        private String title;
        private String content;
    }

    /**
     * read one
     */
    @Data
    @AllArgsConstructor
    public static class DetailDto {
        private String createBy;
        private String modifyBy;
        private String title;
        private String content;
        private int viewCount;
        private LocalDateTime createDate;
        private LocalDateTime lastModifiedDate;
    }


    /**
     * read
     */
    @Data
    @AllArgsConstructor
    public static class PageDto {
        private String createBy;
        private String modifyBy;
        private String title;
        private int viewCount;
        private LocalDateTime createDate;
        private LocalDateTime lastModifiedDate;
    }
}
