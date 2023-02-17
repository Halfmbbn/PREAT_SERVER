package com.andes.preat.service.auth;

import com.andes.preat.dto.response.auth.kakao.KakaoProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoAuthClient", url = "https://kapi.kakao.com")
public interface KakaoAuthClient {
    @PostMapping("/v2/user/me")
    KakaoProfileResponse getProfileInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
                                        @RequestHeader("Content-Type") String contentType);
}
