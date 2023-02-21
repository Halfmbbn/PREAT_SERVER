package com.andes.preat.dto.request.review;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.review.Review;
import com.andes.preat.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReviewRequest {
    @NotNull(message = "별점이 없습니다.")
    private Double rating;
    public Review toReview(final User user, final Restaurant restaurant) {
        return Review.from(rating, user, restaurant);
    }
}
