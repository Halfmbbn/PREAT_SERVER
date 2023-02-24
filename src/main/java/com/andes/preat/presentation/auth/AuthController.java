package com.andes.preat.presentation.auth;

import com.andes.preat.dto.request.auth.KakaoLoginRequest;
import com.andes.preat.dto.request.auth.SignUpReviewRequest;
import com.andes.preat.dto.request.auth.UserSignUpRequest;
import com.andes.preat.dto.request.auth.UserSignUpTastyInfoRequest;
import com.andes.preat.dto.request.review.ReviewWithRestaurantIdRequest;
import com.andes.preat.dto.response.auth.LoginResponse;
import com.andes.preat.dto.response.auth.NicknameCheckResponse;
import com.andes.preat.dto.response.auth.signupResponse;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.exception.badRequest.NotFoundPlatformException;
import com.andes.preat.service.auth.AuthService;
import com.andes.preat.service.auth.jwt.UserPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody final KakaoLoginRequest request) {
        if (!request.getPlatform().equals("kakao")) {
            throw new NotFoundPlatformException();
        }
        LoginResponse loginResponse = authService.loginUser(request.getSocialToken());
        return ResponseEntity.ok().body(new BaseResponse(loginResponse));
    }
    @PostMapping("/signup")
    @Login
    public ResponseEntity<BaseResponse> signup(@VerifiedMember final UserPayload userPayload,
                                         @RequestBody @Valid final UserSignUpRequest request) {
        signupResponse signupResponse = authService.signUp(userPayload.getId(), request);
        return ResponseEntity.ok().body(new BaseResponse(signupResponse));
    }
    @PostMapping("/signupTest")
    public ResponseEntity<BaseResponse> signupForTest(@RequestParam final Long userId,
                                               @Valid @RequestBody final UserSignUpRequest request) {
        List<ReviewWithRestaurantIdRequest> reviews = request.getReviews();
        System.out.println("reviews = " + reviews);
        authService.signUp(userId, request);

        return ResponseEntity.ok().body(new BaseResponse(userId));
    }
    @GetMapping("/nickname/check")
    public ResponseEntity<BaseResponse> checkNickname(@RequestParam(required = true) final String nickname) {
        NicknameCheckResponse nicknameCheckResponse = authService.checkNicknameExist(nickname);
        return ResponseEntity.ok().body(new BaseResponse(nicknameCheckResponse));
    }
}
