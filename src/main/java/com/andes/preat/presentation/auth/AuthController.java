package com.andes.preat.presentation.auth;

import com.andes.preat.dto.request.auth.KakaoLoginRequest;
import com.andes.preat.dto.response.auth.LoginResponse;
import com.andes.preat.dto.response.auth.LoginUserResponse;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.service.auth.AuthService;
import com.andes.preat.service.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/v1/login/kakao")
    public ResponseEntity<BaseResponse> login(@RequestBody final KakaoLoginRequest request) {
        if (!request.getPlatform().equals("kakao")) {
            throw  new IllegalArgumentException("잘못된 플랫폼 입력입니다.");
        }
        LoginResponse loginResponse = authService.loginUser(request.getSocialToken());
        return ResponseEntity.ok().body(new BaseResponse(loginResponse));
    }
    @GetMapping("/v1/signup/code")
    public ResponseEntity<String> authorize(@RequestParam final String code) {
        System.out.println("code = " + code);
        return ResponseEntity.ok().body(code);
    }
    @GetMapping("/v1/signup/accesstoken")
    public ResponseEntity<String> access(@RequestParam final String code) {
        System.out.println("code = " + code);
        return ResponseEntity.ok().body(code);
    }
}
