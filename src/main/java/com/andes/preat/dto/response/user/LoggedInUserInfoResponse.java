package com.andes.preat.dto.response.user;

import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.auth.LoginUserResponse;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggedInUserInfoResponse {
    private Long id;
    private String nickname;
    private String profileImgUrl;

    public static LoggedInUserInfoResponse from(User loggedInUser) {
        return new LoggedInUserInfoResponse(loggedInUser.getId(),
                loggedInUser.getNickname(),
                loggedInUser.getProfileImgUrl());
    }
}
