package com.andes.preat.domain.review;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.user.User;
import com.andes.preat.exception.badRequest.InvalidRatingValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "REVIEWS")
public class Review extends BaseEntity {
    private static final double MINIMUM_RATING = 0.0;
    private static final double MAXIMUM_RATING = 5.0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double rating;
    @Column
    private Boolean isShown;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Review(Double rating, User user, Restaurant restaurant) {
        validateRating(rating);
        this.rating = rating;
        this.user = user;
        this.restaurant = restaurant;
        this.isShown = true;
    }
    public static Review from(Double rating, User user, Restaurant restaurant) {
        return new Review(rating, user, restaurant);
    }
    private void validateRating(Double rating) {
        if (rating < MINIMUM_RATING || rating > MAXIMUM_RATING) {
            throw new InvalidRatingValueException();
        }
    }
    public void deleteFromMylist() {
        this.isShown = false;
    }
    public boolean isWrittenBy(User user) {
        return this.user.equals(user);
    }
    public void update(Review updateReview) {
        rating = updateReview.getRating();
    }
}
