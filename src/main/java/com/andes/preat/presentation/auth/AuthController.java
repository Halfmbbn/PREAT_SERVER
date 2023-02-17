package com.andes.preat.presentation.auth;

import com.andes.preat.dto.request.auth.KakaoLoginRequest;
import com.andes.preat.dto.response.auth.LoginResponse;
import com.andes.preat.dto.response.auth.LoginUserResponse;
import com.andes.preat.service.auth.AuthService;
import com.andes.preat.service.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @GetMapping("/v1/signup/kakao")
    public ResponseEntity<LoginResponse> loginKakao(@RequestBody final KakaoLoginRequest request) {
        System.out.println("code = " + request.getAccessToken());
        LoginResponse loginResponse = authService.getProfileInfo(request.getAccessToken());
        return ResponseEntity.ok().body(loginResponse);
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
