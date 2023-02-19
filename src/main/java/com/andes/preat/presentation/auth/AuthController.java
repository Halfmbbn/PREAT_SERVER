package com.andes.preat.presentation.auth;

import com.andes.preat.dto.request.auth.KakaoLoginRequest;
import com.andes.preat.dto.request.auth.UserSignUpRequest;
import com.andes.preat.dto.request.auth.UserSignUpTastyInfoRequest;
import com.andes.preat.dto.response.auth.LoginResponse;
import com.andes.preat.dto.response.auth.NicknameCheckResponse;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.exception.badRequest.NotFoundPlatform;
import com.andes.preat.service.auth.AuthService;
import com.andes.preat.service.auth.jwt.UserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody final KakaoLoginRequest request) {
        if (!request.getPlatform().equals("kakao")) {
            throw new NotFoundPlatform();
        }
        LoginResponse loginResponse = authService.loginUser(request.getSocialToken());
        return ResponseEntity.ok().body(new BaseResponse(loginResponse));
    }
    @PostMapping("/signup")
    @Login
    public ResponseEntity<BaseResponse> signup(@VerifiedMember final UserPayload userPayload,
                                         @RequestBody final UserSignUpRequest request) {
        authService.signUp(userPayload.getId(), request.getNickname(), UserSignUpTastyInfoRequest.from(request));
        return ResponseEntity.ok().body(new BaseResponse(userPayload.getId()));
    }
    @GetMapping("/nicknameCheck")
    public ResponseEntity<BaseResponse> checkNickname(@RequestParam(required = true) final String nickname) {
        NicknameCheckResponse nicknameCheckResponse = authService.checkNicknameExist(nickname);
        return ResponseEntity.ok().body(new BaseResponse(nicknameCheckResponse));
    }
}
