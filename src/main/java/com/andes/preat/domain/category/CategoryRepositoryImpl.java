package com.andes.preat.domain.category;

import com.andes.preat.domain.restaurant.QRestaurant;
import com.andes.preat.domain.review.QReview;
import com.andes.preat.dto.response.user.HighScoredCategoryResponse;
import com.andes.preat.dto.response.user.MostVisitedCategoryResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<MostVisitedCategoryResponse> findByUserMostCategory(Long userId) {
        List<MostVisitedCategoryResponse> responses = jpaQueryFactory.select(Projections.constructor(MostVisitedCategoryResponse.class,
                        QRestaurant.restaurant.category.id, QRestaurant.restaurant.category.name, QRestaurant.restaurant.category.id.count()))
                .from(QReview.review)
                .join(QReview.review.restaurant, QRestaurant.restaurant)
                .where(QReview.review.user.id.eq(userId))
                .groupBy(QRestaurant.restaurant.category)
                .orderBy(QRestaurant.restaurant.category.id.count().desc())
                .limit(5)
                .fetch();

        if (responses.isEmpty()) {
            return null;
        }
        return responses;
    }

    @Override
    public List<MostVisitedCategoryResponse> findByUserMostCategoryDetail(Long userId) {
        List<MostVisitedCategoryResponse> responses = jpaQueryFactory.select(Projections.constructor(MostVisitedCategoryResponse.class,
                        QRestaurant.restaurant.category.id, QRestaurant.restaurant.category.name, QRestaurant.restaurant.category.id.count()))
                .from(QReview.review)
                .join(QReview.review.restaurant, QRestaurant.restaurant)
                .where(QReview.review.user.id.eq(userId))
                .groupBy(QRestaurant.restaurant.category)
                .orderBy(QRestaurant.restaurant.category.id.count().desc())
                .fetch();
        System.out.println("longs = " + responses);
        return responses;
    }

    @Override
    public List<HighScoredCategoryResponse> findByUserHighScoredCategory(Long userId) {
        List<HighScoredCategoryResponse> responses = jpaQueryFactory.select(Projections.constructor(HighScoredCategoryResponse.class,
                        QRestaurant.restaurant.category.id, QRestaurant.restaurant.category.name, QReview.review.rating.max()))
                .from(QReview.review)
                .join(QReview.review.restaurant, QRestaurant.restaurant)
                .where(QReview.review.user.id.eq(userId))
                .groupBy(QRestaurant.restaurant.category)
                .orderBy(QReview.review.rating.max().desc())
                .limit(5)
                .fetch();
        if (responses.isEmpty()) {
            return null;
        }
        return responses;
    }
    @Override
    public List<HighScoredCategoryResponse> findByUserHighScoredCategoryDetail(Long userId) {
        List<HighScoredCategoryResponse> responses = jpaQueryFactory.select(Projections.constructor(HighScoredCategoryResponse.class,
                        QRestaurant.restaurant.category.id, QRestaurant.restaurant.category.name, QReview.review.rating.max()))
                .from(QReview.review)
                .join(QReview.review.restaurant, QRestaurant.restaurant)
                .where(QReview.review.user.id.eq(userId))
                .groupBy(QRestaurant.restaurant.category)
                .orderBy(QReview.review.rating.max().desc())
                .fetch();
        return responses;
    }
}
