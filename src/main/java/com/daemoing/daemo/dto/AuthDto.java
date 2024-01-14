package com.daemoing.daemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AuthDto {

    /**
     * 로그인
     */
    @Data
    @AllArgsConstructor
    public static class LoginDto {
        private String loginId;
        private String password;

    }


    /**
     * AT,RT 토큰
     */
    @Data
    @AllArgsConstructor
    public static class TokenDto {
        private String accessToken;
        private String refreshToken;
    }
}
