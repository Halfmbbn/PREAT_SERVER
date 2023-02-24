package com.andes.preat.dto.response.auth;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class signupResponse {
    private Long id;
    private String accessToken;

    public static signupResponse from(final Long id, final String accessToken) {
        return new signupResponse(id, accessToken);
    }
}
