package com.andes.preat.dto.response.auth;

import com.andes.preat.domain.user.User;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUserResponse {
    private Long id;
    private String email;
    private String nickname;
    private String gender;
    private String ageRange;
    private String profileImgUrl;

    public static LoginUserResponse from(User loggedInUser) {
        return new LoginUserResponse(loggedInUser.getId(),
                loggedInUser.getEmail(),
                loggedInUser.getNickname(),
                loggedInUser.getGender(),
                loggedInUser.getAgeRange(),
                loggedInUser.getProfileImgUrl());
    }
}
