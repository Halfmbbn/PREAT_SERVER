package com.andes.preat.dto.response.user;

import com.andes.preat.domain.user.User;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowsInfoResponse {
    private Long id;
    private String nickname;
    private String profileImgUrl;
    private List<FollowUserInfoResponse> follows;

    public static FollowsInfoResponse from(User foundUser, List<FollowUserInfoResponse> responses) {
        if (responses.isEmpty()) {
            return new FollowsInfoResponse(foundUser.getId(), foundUser.getNickname(), foundUser.getProfileImgUrl(), null);
        }
        return new FollowsInfoResponse(foundUser.getId(), foundUser.getNickname(), foundUser.getProfileImgUrl(), responses);
    }
}
