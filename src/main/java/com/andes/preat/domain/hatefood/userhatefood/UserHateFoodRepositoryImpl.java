package com.andes.preat.domain.hatefood.userhatefood;

import com.andes.preat.domain.hatefood.QHateFood;
import com.andes.preat.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class UserHateFoodRepositoryImpl implements UserHateFoodRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<UserHateFood> findAllByUser(User user) {

        return jpaQueryFactory.selectFrom(QUserHateFood.userHateFood)
                .leftJoin(QUserHateFood.userHateFood.hateFood,QHateFood.hateFood)
                .fetchJoin()
                .where(QUserHateFood.userHateFood.user.eq(user))
                .fetch();
    }

    @Override
    public Long deleteAllByUserId(Long userId) {
        long executes = jpaQueryFactory.delete(QUserHateFood.userHateFood)
                .where(QUserHateFood.userHateFood.user.id.eq(userId))
                .execute();
        entityManager.flush();
        entityManager.clear();
        return executes;
    }
}
