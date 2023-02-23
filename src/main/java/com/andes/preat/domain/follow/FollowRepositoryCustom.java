package com.andes.preat.domain.follow;

import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.user.FollowUserInfoResponse;
import com.andes.preat.dto.response.user.SimilarFollowsResponse;

import java.util.List;

public interface FollowRepositoryCustom {
    List<FollowUserInfoResponse> findAllByUserId(User user);
    List<SimilarFollowsResponse> findSimilarByUser(User user);
}
