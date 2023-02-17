package com.andes.preat.dto.response.auth;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private String accessToken;
    private LoginUserResponse userInfo;

    public static LoginResponse from(LoginUserResponse loginUser, String accessToken) {
        return new LoginResponse(accessToken, loginUser);
    }
}
