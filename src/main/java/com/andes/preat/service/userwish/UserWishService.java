package com.andes.preat.service.userwish;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.restaurant.RestaurantRepository;
import com.andes.preat.domain.review.Review;
import com.andes.preat.domain.review.ReviewRepository;
import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.domain.userwish.UserWish;
import com.andes.preat.domain.userwish.UserWishRepository;
import com.andes.preat.dto.request.review.ReviewListRequest;
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
public class UserWishService {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserWishRepository userWishRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long saveUserWish(final Long restaurantId, final Long userId) {
        final User foundUser = findUserById(userId);
        validateRegisterCompleted(foundUser);
        final Restaurant foundRestaurant = findRestaurantById(restaurantId);
        final Long reviewId = saveUserWish(foundUser, foundRestaurant);
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
    private Long saveUserWish(final User user, final Restaurant restaurant) {
        validateNotWritten(user, restaurant);
        UserWish userWish = UserWish.from(user, restaurant);
        return userWishRepository.save(userWish)
                .getId();
    }
    private void validateNotWritten(final User user, final Restaurant restaurant) {
        if (userWishRepository.existsByUserAndRestaurant(user, restaurant)) {
            throw new AlreadyWrittenUserWishException();
        }
    }
//    @Transactional
//    public void saveReviewsWithList(final Long userId,
//                                    final ReviewListRequest reviewListRequest) {
//        final User foundUser = findUserById(userId);
//        validateRegisterCompleted(foundUser);
//        saveReviewsWithList(reviewListRequest.getReviews(), foundUser);
//    }
//    private void saveReviewsWithList(final List<ReviewWithRestaurantIdRequest> reviews, final User user) {
//        for (ReviewWithRestaurantIdRequest rev : reviews) {
//            Restaurant restaurant = findRestaurantById(rev.getRestaurantId());
//            validateNotWritten(user, restaurant);
//            final Review review = rev.toReview(user, restaurant);
//            reviewRepository.save(review);
//        }
//    }
//    @Transactional
//    public void update(final Long restaurantId, final Long userId) {
//        UserWish foundUserWish = findTarget(restaurantId, userId);
//        UserWish updateUserWish = UserWish.from(foundUserWish.getUser(), foundUserWish.getRestaurant());
//        foundUserWish.update(updateUserWish);
////        int ratingGap = updateReview.getRating() - target.getRating();
////        productRepository.updateProductStatisticsForReviewUpdate(target.getProduct().getId(), ratingGap);
//    }
    private UserWish findTarget(final Long restaurantId, final Long userId) {
        final User foundUser = findUserById(userId);
        final Restaurant foundRestaurant = findRestaurantById(restaurantId);
        UserWish foundUserWish = findUserWishByUserAndRestaurant(foundUser, foundRestaurant);
        validateAuthor(foundUser, foundUserWish);
        return foundUserWish;
    }
    private UserWish findUserWishByUserAndRestaurant(final User foundUser, final Restaurant foundRestaurant) {
        return userWishRepository.findByUserAndRestaurant(foundUser, foundRestaurant)
                .orElseThrow(UserWishNotFoundException::new);
    }
    private void validateAuthor(final User foundUser, final UserWish userWish) {
        if (!userWish.isWrittenBy(foundUser)) {
            throw new NotAuthorException();
        }
    }
    @Transactional
    public void delete(final Long restaurantId, final Long userId) {
        UserWish foundUserWish = findTarget(restaurantId, userId);
        userWishRepository.delete(foundUserWish);
//        productRepository.updateProductStatisticsForReviewDelete(review.getProduct().getId(), review.getRating());
    }
    // ID List로 삭제
    @Transactional
    public void deleteAllByIdIn(final Long userId, final List<Long> requestIds) {
        userWishRepository.deleteAllByIdInQuery(requestIds);
//        productRepository.updateProductStatisticsForReviewDelete(review.getProduct().getId(), review.getRating());
    }

    public RestaurantsResponse findAllByUser(final Long userId) {
        final User foundUser = findUserById(userId);
        List<UserWish> userWishes = userWishRepository.findAllByUser(foundUser);
        List<RestaurantInfoResponse> restaurantInfoResponses = userWishes.stream()
                .map(u -> calculatePredict(foundUser, u.getRestaurant()))
                .collect(Collectors.toList());
        return RestaurantsResponse.from(restaurantInfoResponses);
    }

    private RestaurantInfoResponse calculatePredict(User user, Restaurant restaurant) {
        // TODO : 예상 별점 로직 추가
        return RestaurantInfoResponse.from(restaurant, 3.3);
    }
}
