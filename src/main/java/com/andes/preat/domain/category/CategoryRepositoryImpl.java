package com.andes.preat.domain.category;

import com.andes.preat.domain.restaurant.QRestaurant;
import com.andes.preat.domain.review.QReview;
import com.andes.preat.dto.response.user.CategoryStaticsResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Category> findByUserGroupByCategory(Long userId) {
        List<?> fetch = jpaQueryFactory.select(Projections.constructor(CategoryStaticsResponse.class,
                        QRestaurant.restaurant.category.id,QRestaurant.restaurant.category.name, QRestaurant.restaurant.category.id.count()))
                .from(QReview.review)
                .join(QReview.review.restaurant, QRestaurant.restaurant)
                .where(QReview.review.user.id.eq(userId))
                .groupBy(QRestaurant.restaurant.category)
                .orderBy(QRestaurant.restaurant.category.id.count().desc()).fetch();
        System.out.println("longs = " + fetch);
        return null;
    }
}
