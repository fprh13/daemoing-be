package com.daemoing.daemo.controller.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto<T> {

    private boolean success;

    private int status;

    private String code;

    private String message;

    private T response;


    @Builder
    public ResponseDto(boolean success, String code ,String message, int status, T response) {
        this.success = success;
        this.status = status;
        this.code = code;
        this.message = message;
        this.response = response;
    }

    /**
     * 성공 응답
     */
    public static <T> ResponseDto<T> success(HttpStatus status, T data) {
        return ResponseDto.<T>builder()
                .success(true)
                .status(status.value())
                .code(status.name())
                .message("성공적으로 처리되었습니다.")
                .response(data)
                .build();
    }

    /**
     * 실패 응답
     */
    public static <T> ResponseDto<T> fail(HttpStatus status, String message) {
        return ResponseDto.<T>builder()
                .success(false)
                .status(status.value())
                .code(status.name())
                .message(message)
                .response(null)
                .build();
    }

}

