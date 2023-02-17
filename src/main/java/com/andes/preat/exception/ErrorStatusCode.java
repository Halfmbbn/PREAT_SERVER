package com.andes.preat.exception;

import lombok.Getter;

@Getter
public enum ErrorStatusCode {

    TOKEN_INVALID_FORM(401, "잘못된 토큰 형식입니다."),
    TOKEN_HEADER_EMPTY(401, "헤더에 토큰이 포함되어 있지 않습니다."),
    TOKEN_EXPIRED(401,"토큰이 만료되었습니다."),
    TOKEN_NOT_REQUIRED(401,"토큰이 필요없는 요청입니다.");

    private final int status;
    private final String message;
    private ErrorStatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
