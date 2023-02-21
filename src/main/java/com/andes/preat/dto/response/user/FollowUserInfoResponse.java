package com.andes.preat.dto.response.user;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FollowUserInfoResponse {
    private Long id;
    private String nickname;
    private String profileImgUrl;
}
