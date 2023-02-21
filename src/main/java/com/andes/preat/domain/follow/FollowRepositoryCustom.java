package com.andes.preat.domain.follow;

import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.user.FollowUserInfoResponse;

import java.util.List;

public interface FollowRepositoryCustom {
    List<FollowUserInfoResponse> findAllByUserId(User user);
}
