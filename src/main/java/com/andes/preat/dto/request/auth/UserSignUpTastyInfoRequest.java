package com.andes.preat.dto.request.auth;

import lombok.*;

import java.util.List;
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignUpTastyInfoRequest {
    private Integer sweet;
    private Integer spicy;
    private Integer salty;

    public static UserSignUpTastyInfoRequest from(UserSignUpRequest request) {
        return new UserSignUpTastyInfoRequest(request.getSweet(),
                request.getSpicy(), request.getSalty());
    }
}
