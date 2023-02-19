package com.andes.preat.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NicknameCheckResponse {
    private Boolean isAvailable;

    public static NicknameCheckResponse from(boolean isAvailable) {
        return new NicknameCheckResponse(isAvailable);
    }
}
