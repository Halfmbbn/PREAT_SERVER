package com.andes.preat.service.review;

import com.andes.preat.domain.follow.Follow;
import com.andes.preat.domain.follow.FollowRepository;
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
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import com.andes.preat.dto.response.restaurant.RestaurantsResponse;
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
    private final static double MYLIST_CUT_OFF_SCORE = 3.0;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserWishRepository userWishRepository;
    private final FollowRepository followRepository;

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
    public void saveReviewsWithList(final List<ReviewWithRestaurantIdRequest> reviews, final User foundUser) {
        validateRegisterCompleted(foundUser);
        for (ReviewWithRestaurantIdRequest rev : reviews) {
            Restaurant restaurant = findRestaurantById(rev.getRestaurantId());
            validateNotWritten(foundUser, restaurant);
            final Review review = rev.toReview(foundUser, restaurant);
            reviewRepository.save(review);
        }
    }
    @Transactional
    public void update(final Long restaurantId, final Long userId, final ReviewRequest updateRequest) {
        final Review foundReview = findTarget(restaurantId, userId);
        final Review updateReview = updateRequest.toReview(foundReview.getUser(), foundReview.getRestaurant());
        foundReview.update(updateReview);
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
    // 레코드 삭제
    @Transactional
    public void delete(final Long restaurantId, final Long userId) {
        final Review foundReview = findTarget(restaurantId, userId);
        reviewRepository.delete(foundReview);
    }
    @Transactional
    public void deleteAllByUserIdAndRestaurantIdIn(final List<Long> restaurantIds, final Long userId) {
        if (!restaurantIds.isEmpty()) {
            Integer deletes = reviewRepository.deleteAllByUserIdAndRestaurantIdIn(restaurantIds, userId);
            System.out.println("deletes = " + deletes);
        }
    }
    @Transactional
    public void deleteFromMylist(final Long restaurantId, final Long userId) {
        final Review foundReview = findTarget(restaurantId, userId);
        foundReview.deleteFromMylist();
    }
    @Transactional
    public void deleteAllFromMylistByUserIdAndRestaurantIdIn(final List<Long> restaurantIds, final Long userId) {
        if (!restaurantIds.isEmpty()) {
            for (Long restaurantId: restaurantIds) {
                Review foundReview = findTarget(restaurantId, userId);
                foundReview.deleteFromMylist();
            }
        }
    }
    public RestaurantsResponse findByUserAndIsShownTrue(final Long userId) {
        User foundUser = findUserById(userId);
        List<Review> foundReviews = reviewRepository.findByUserAndIsShownTrueAndRatingGreaterThanEqual(foundUser,MYLIST_CUT_OFF_SCORE);
        if (foundReviews.isEmpty()) {
            return RestaurantsResponse.from(null);
        }
        List<RestaurantInfoResponse> restaurants = foundReviews.stream()
                .map(r -> RestaurantInfoResponse
                        .from(r.getRestaurant(), r))
                .collect(Collectors.toList());
        return RestaurantsResponse.from(restaurants);
    }
    // TODO: 팔로우한 사람들의 리뷰
//    public RestaurantsResponse findByFollowsAndIsShownTrue(final Long userId) {
//        User foundUser = findUserById(userId);
//        List<Follow> follows = followRepository.findByFollower(foundUser);
//        if (follows.isEmpty()) {
//            return RestaurantsResponse.from(null);
//        }
//        List<Long> followingUserIds = follows.stream()
//                .map(f -> f.getFollowing().getId())
//                .collect(Collectors.toList());
//        List<Review> reviews = reviewRepository.findAllByUserIdAndIsShownTrueAndRatingGreaterThanEqual(followingUserIds);
//        if (reviews.isEmpty()) {
//            return RestaurantsResponse.from(null);
//        }
//
//    }
//
//    private RestaurantInfoResponse findReviewFromFollowers(final User foundUser, final Restaurant restaurant) {
//        if (reviewRepository.existsByUserAndRestaurant(foundUser, restaurant)) {
//            return RestaurantInfoResponse.from(restaurant, reviewRepository.findByUserAndRestaurant(foundUser, restaurant).get());
//        }
//        return RestaurantInfoResponse.from(restaurant, 3.3);
//    }

}
