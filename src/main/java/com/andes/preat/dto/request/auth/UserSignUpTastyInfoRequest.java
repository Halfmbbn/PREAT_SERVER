package com.andes.preat.dto.request.auth;

import lombok.*;

import java.util.List;
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignUpTastyInfoRequest {
    private String nickname;
    private Integer salty;
    private Integer sweet;
    private Integer spicy;

    public static UserSignUpTastyInfoRequest from(UserSignUpRequest request) {
        return new UserSignUpTastyInfoRequest(request.getNickname(),
                request.getSalty(),
                request.getSweet(),
                request.getSpicy());
    }
}
