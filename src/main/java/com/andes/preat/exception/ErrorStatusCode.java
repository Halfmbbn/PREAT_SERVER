package com.andes.preat.exception;

import lombok.Getter;

@Getter
public enum ErrorStatusCode {
    FOLLOW_NOT_FOUND_FOLLOW(400, "팔로우 관계를 찾을 수 없습니다."),
    FOLLOW_ALREADY_FOLLOWING(400, "이미 팔로우 요청이 완료되었습니다."),
    FOLLOW_CANT_SELF_FOLLOW(400, "자신에게 팔로우 요청을 할 수 없습니다."),
    USER_NOT_FOUND_PLATFORM(400,"잘못된 소셜 플랫폼입니다."),
    USER_NOT_FOUND(400, "유저 정보를 찾을 수 없습니다."),
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
