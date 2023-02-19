package com.andes.preat.service.auth;

import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.dto.request.auth.UserSignUpTastyInfoRequest;
import com.andes.preat.dto.response.auth.LoginResponse;
import com.andes.preat.dto.response.auth.LoginUserResponse;
import com.andes.preat.dto.response.auth.NicknameCheckResponse;
import com.andes.preat.dto.response.auth.kakao.KakaoProfileResponse;
import com.andes.preat.exception.badRequest.NotFoundUser;
import com.andes.preat.service.auth.jwt.JwtProvider;
import com.andes.preat.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserService userService;
    private final KakaoAuthClient kakaoAuthClient;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Transactional
    public LoginResponse loginUser(final String code) {
        System.out.println("code = " + code);
        // kakao 조회
        String accessToken = "Bearer " + code;
        KakaoProfileResponse profileInfo = kakaoAuthClient.getProfileInfo(accessToken, "application/x-www/form-urlencoded");
        // 내 db 에서 확인
        boolean isNewUser = !checkUserExist(profileInfo);
        // 불린 값
        System.out.println("profileInfo = " + profileInfo.toString());
        final User loggedInUser = addOrUpdateMember(profileInfo);
        // jwt 발급
        String applicationAccessToken = jwtProvider.createAccessToken(loggedInUser.getId());
        return LoginResponse.from(isNewUser, applicationAccessToken);
    }
    @Transactional
    public void signUp(final Long userId, final String nickname, final UserSignUpTastyInfoRequest tastyInfoRequest) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUser());
        // TODO: nickname과 tastyInfo, 싫어하는 음식 update 분리
        foundUser.updateNickname(nickname);
        foundUser.updateTastyInfo(tastyInfoRequest);
    }

    private User addOrUpdateMember(final KakaoProfileResponse kakaoProfileResponse) {
        final User requestedUser = kakaoProfileResponse.toUser();
        final User user = userRepository.findByEmail(kakaoProfileResponse.getKakaoAccount().getEmail())
                .orElseGet(() -> userRepository.save(requestedUser));
        user.update(requestedUser);
        return user;
    }
    public boolean checkUserExist(final KakaoProfileResponse kakaoProfileResponse) {
        if (!userRepository.existsUserByEmail(kakaoProfileResponse.getKakaoAccount().getEmail())) {
            return false;
        }
        return true;
    }

    public NicknameCheckResponse checkNicknameExist(final String requestNickname) {
        return NicknameCheckResponse.from(!checkUserExist(requestNickname));
    }
    public boolean checkUserExist(final String requestNickname) {
        if (!userRepository.existsUserByNickname(requestNickname)) {
            return false;
        }
        return true;
    }
}