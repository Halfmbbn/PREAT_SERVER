package com.andes.preat.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isNewUser", "accessToken"})
public class LoginResponse {
    private boolean isNewUser;
    private String accessToken;
    public static LoginResponse from(boolean isNewUser, String accessToken) {
        return new LoginResponse(isNewUser, accessToken);
    }
}
