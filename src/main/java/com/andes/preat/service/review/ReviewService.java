package com.andes.preat.service.review;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.restaurant.RestaurantRepository;
import com.andes.preat.domain.review.Review;
import com.andes.preat.domain.review.ReviewRepository;
import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.domain.userwish.UserWishRepository;
import com.andes.preat.dto.request.review.ReviewRequest;
import com.andes.preat.dto.request.review.ReviewListRequest;
import com.andes.preat.dto.request.review.ReviewWithRestaurantIdRequest;
import com.andes.preat.exception.badRequest.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserWishRepository userWishRepository;

    @Transactional
    public Long saveReview(final Long restaurantId, final Long userId,
                                              final ReviewRequest reviewRequest) {
        final User foundUser = findUserById(userId);
        validateRegisterCompleted(foundUser);
        final Restaurant foundRestaurant = findRestaurantById(restaurantId);
        final Long reviewId = saveReview(reviewRequest, foundUser, foundRestaurant);
        deleteUserWish(foundUser, foundRestaurant);
//        saveInventoryProduct(member, product);
//        productRepository.updateProductStatisticsForReviewInsert(product.getId(), reviewRequest.getRating());
        return reviewId;
    }
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }
    private Restaurant findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(RestaurantNotFoundException::new);
    }
    private void validateRegisterCompleted(final User user) {
        if (!user.isRegistered()) {
            throw new RegisterNotCompletedException();
        }
    }
    private Long saveReview(final ReviewRequest reviewRequest, final User user, final Restaurant restaurant) {
        validateNotWritten(user, restaurant);
        final Review review = reviewRequest.toReview(user, restaurant);
        return reviewRepository.save(review)
                .getId();
    }
    private void validateNotWritten(final User user, final Restaurant restaurant) {
        if (reviewRepository.existsByUserAndRestaurant(user, restaurant)) {
            throw new AlreadyWrittenReviewException();
        }
    }
    private void deleteUserWish(User foundUser, Restaurant foundRestaurant) {
        if (userWishRepository.existsByUserAndRestaurant(foundUser, foundRestaurant)) {
            // find 하고 delete
            userWishRepository.deleteByUserAndRestaurant(foundUser, foundRestaurant);
        }
    }
    @Transactional
    public void saveReviewsWithList(final Long userId,
                           final List<ReviewWithRestaurantIdRequest> reviews) {
        final User foundUser = findUserById(userId);
        validateRegisterCompleted(foundUser);
        saveReviewsWithList(reviews, foundUser);
    }
    @Transactional
    public void saveReviewsWithList(final User foundUser,
                                    final List<ReviewWithRestaurantIdRequest> reviews) {
//        final User foundUser = findUserById(userId);
        validateRegisterCompleted(foundUser);
        saveReviewsWithList(reviews, foundUser);
    }
    private void saveReviewsWithList(final List<ReviewWithRestaurantIdRequest> reviews, final User user) {
        for (ReviewWithRestaurantIdRequest rev : reviews) {
            Restaurant restaurant = findRestaurantById(rev.getRestaurantId());
            validateNotWritten(user, restaurant);
            final Review review = rev.toReview(user, restaurant);
            reviewRepository.save(review);
        }
    }
    @Transactional
    public void update(final Long restaurantId, final Long userId, final ReviewRequest updateRequest) {
        final Review foundReview = findTarget(restaurantId, userId);
        final Review updateReview = updateRequest.toReview(foundReview.getUser(), foundReview.getRestaurant());
        foundReview.update(updateReview);
//        int ratingGap = updateReview.getRating() - target.getRating();
//        productRepository.updateProductStatisticsForReviewUpdate(target.getProduct().getId(), ratingGap);
    }
    private Review findTarget(final Long restaurantId, final Long userId) {
        final User foundUser = findUserById(userId);
        final Restaurant foundRestaurant = findRestaurantById(restaurantId);
        final Review foundReview = findReviewByUserAndRestaurant(foundUser, foundRestaurant);
        validateAuthor(foundUser, foundReview);
        return foundReview;
    }
    private Review findReviewByUserAndRestaurant(final User foundUser, final Restaurant foundRestaurant) {
        return reviewRepository.findByUserAndRestaurant(foundUser, foundRestaurant)
                .orElseThrow(ReviewNotFoundException::new);
    }
    private void validateAuthor(final User foundUser, final Review foundReview) {
        if (!foundReview.isWrittenBy(foundUser)) {
            throw new NotAuthorException();
        }
    }
    @Transactional
    public void delete(final Long restaurantId, final Long userId) {
        final Review review = findTarget(restaurantId, userId);
        reviewRepository.delete(review);
//        productRepository.updateProductStatisticsForReviewDelete(review.getProduct().getId(), review.getRating());
    }
    @Transactional
    public void deleteAllByUserIdAndRestaurantIdIn(final List<Long> restaurantIds, final Long userId) {
        if (!restaurantIds.isEmpty()) {
            Integer deletes = reviewRepository.deleteAllByUserIdAndRestaurantIdIn(restaurantIds, userId);
            System.out.println("deletes = " + deletes);
        }
    }
}
