package com.andes.preat.domain.follow;

import com.andes.preat.domain.user.QUser;
import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.user.FollowUserInfoResponse;
import com.andes.preat.dto.response.user.SimilarFollowsResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
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

    @Override
    public List<SimilarFollowsResponse> findSimilarByUser(User user) {
        StringPath aliasSimilar = Expressions.stringPath("similar");
        List<SimilarFollowsResponse> responses = jpaQueryFactory.select(Projections.constructor(SimilarFollowsResponse.class, QUser.user.id, QUser.user.nickname,
                        QUser.user.salty.castToNum(Integer.class).subtract(user.getSalty()).abs()
                                .add(QUser.user.sweet.castToNum(Integer.class).subtract(user.getSweet()).abs())
                                .add(QUser.user.spicy.castToNum(Integer.class).subtract(user.getSpicy()).abs()).as("similar")))
                .from(QFollow.follow)
                .join(QFollow.follow.following, QUser.user)
                .where(QFollow.follow.follower.eq(user))
                .orderBy(aliasSimilar.asc())
                .limit(3)
                .fetch();
        if (responses.isEmpty()) {
            return null;
        }
        return responses;
    }
}
