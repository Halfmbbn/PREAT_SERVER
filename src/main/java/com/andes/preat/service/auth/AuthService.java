package com.andes.preat.service.auth;

import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.dto.response.auth.LoginResponse;
import com.andes.preat.dto.response.auth.LoginUserResponse;
import com.andes.preat.dto.response.auth.kakao.KakaoProfileResponse;
import com.andes.preat.service.auth.jwt.JwtProvider;
import com.andes.preat.service.user.UserService;
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
    public LoginResponse getProfileInfo(final String code) {
        System.out.println("Bearer " + code);
        String accessToken = "Bearer " + code;
        KakaoProfileResponse profileInfo = kakaoAuthClient.getProfileInfo(accessToken, "application/x-www/form-urlencoded");
        System.out.println("profileInfo = " + profileInfo.toString());
        final User loggedInUser = addOrUpdateMember(profileInfo);
        String applicationAccessToken = jwtProvider.createAccessToken(loggedInUser.getId());
        return LoginResponse.from(LoginUserResponse.from(loggedInUser), applicationAccessToken);
    }
    @Transactional
    private User addOrUpdateMember(final KakaoProfileResponse kakaoProfileResponse) {
        final User requestedUser = kakaoProfileResponse.toUser();
        final User user = userRepository.findByEmail(kakaoProfileResponse.getKakaoAccount().getEmail())
                .orElseGet(() -> userRepository.save(requestedUser));
        user.update(requestedUser);
        return user;
    }

}
