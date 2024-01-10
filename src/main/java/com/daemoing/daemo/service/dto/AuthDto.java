package com.daemoing.daemo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class AuthDto {

    /**
     * 로그인
     */
    @Data
    @Builder
    @AllArgsConstructor
    public static class LoginDto {
        private String loginId;
        private String password;

    }


    /**
     * AT,RT 토큰
     */
    @Data
    @Builder
    @AllArgsConstructor
    public static class TokenDto {
        private String accessToken;
        private String refreshToken;
    }
}
