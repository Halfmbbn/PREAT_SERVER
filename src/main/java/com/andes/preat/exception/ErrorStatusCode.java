package com.andes.preat.exception;

import lombok.Getter;

@Getter
public enum ErrorStatusCode {
    USER_WISHES_NOT_AUTHOR(400, "가봐야 할 맛집 작성자가 아닙니다."),
    USER_WISHES_NOT_FOUND(400,"가봐야 할 맛집 정보를 찾을 수 없습니다."),
    USER_WISHES_ALREADY_EXISTS(400, "이미 찜한 식당입니다."),
    RESTAURANT_NOT_FOUND(400, "식당 정보를 찾을 수 없습니다."),
    REVIEWS_NOT_AUTHOR(400, "작성자가 아닙니다."),
    REVIEWS_NOT_FOUND(400,"별점 정보를 찾을 수 없습니다."),
    REVIEWS_ALREADY_EXISTS(400, "중복된 평가입니다."),
    REVIEWS_INVALID_RATING_VALUE(400, "별점 값은 0.0 부터 5.0 사이입니다."),
    METHOD_ARGUMENT_NOT_VALID(400, "RequestBody 또는 RequestParam을 확인해주세요."),
    HATE_FOOD_NOT_FOUND(400, "해당 싫어하는 음식을 찾을 수 없습니다."),
    FOLLOW_NOT_FOUND_FOLLOW(400, "팔로우 관계를 찾을 수 없습니다."),
    FOLLOW_ALREADY_FOLLOWING(400, "이미 팔로우 요청이 완료되었습니다."),
    FOLLOW_CANT_SELF_FOLLOW(400, "자신에게 팔로우 요청을 할 수 없습니다."),
    USER_NOT_FOUND_PLATFORM(400,"잘못된 소셜 플랫폼입니다."),
    USER_NOT_FOUND(400, "유저 정보를 찾을 수 없습니다."),
    USER_NICKNAME_DUPLICATED(400, "중복된 닉네임입니다."),
    USER_NOT_COMPLETE_REGISTER(401,"회원 가입이 완료되지 않았습니다."),
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
