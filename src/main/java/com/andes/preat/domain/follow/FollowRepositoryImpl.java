package com.andes.preat.domain.follow;

import com.andes.preat.domain.user.QUser;
import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.user.FollowUserInfoResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<FollowUserInfoResponse> findAllByUserId(User user) {
        List<Follow> follows = jpaQueryFactory.select(QFollow.follow)
                .from(QFollow.follow)
                .join(QFollow.follow.following, QUser.user).fetchJoin()
                .where(QFollow.follow.follower.eq(user))
                .fetch();
        List<User> followings = follows.stream()
                .map(follow -> follow.getFollowing())
                .collect(Collectors.toList());
        return followings.stream()
                .map(u -> new FollowUserInfoResponse(u.getId(),
                        u.getNickname(),
                        u.getProfileImgUrl()))
                .collect(Collectors.toList());
    }
}
