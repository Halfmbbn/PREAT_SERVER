package com.andes.preat.dto.request.auth;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoLoginRequest {
    private String platform;
    private String socialToken;
}
