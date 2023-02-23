package com.andes.preat.presentation.review;

import com.andes.preat.domain.follow.FollowRepository;
import com.andes.preat.domain.review.Review;
import com.andes.preat.dto.request.review.ReviewRequest;
import com.andes.preat.dto.request.review.ReviewListRequest;
import com.andes.preat.dto.request.review.ReviewsDeleteRequest;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import com.andes.preat.dto.response.restaurant.RestaurantsResponse;
import com.andes.preat.dto.response.user.FollowsRestaurantsResponse;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.presentation.auth.VerifiedMember;
import com.andes.preat.service.auth.jwt.UserPayload;
import com.andes.preat.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ReviewController {
    private final ReviewService reviewService;
    // 단건 별점 등록
    @PostMapping("/restaurants/{restaurantId}/reviews")
    @Login
    public ResponseEntity<BaseResponse> create(@VerifiedMember final UserPayload userPayload,
                                               @PathVariable final Long restaurantId,
                                               @Valid @RequestBody final ReviewRequest reviewRequest) {
        final Long reviewId = reviewService.saveReview(restaurantId, userPayload.getId(), reviewRequest);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
//    @PostMapping("/restaurants/{restaurantId}/reviews/test")
//    public ResponseEntity<BaseResponse> create(@RequestParam final Long userId,
//                                               @PathVariable final Long restaurantId,
//                                               @Valid @RequestBody final ReviewRequest reviewRequest) {
//        final Long reviewId = reviewService.saveReview(restaurantId, userId, reviewRequest);
//        return ResponseEntity.ok().body(new BaseResponse(null));
//    }
    // 단건 별점 수정 -> reviewId로 요청이 가는게 아니라 식당 Id일 것 같은디
    @PatchMapping("/restaurants/{restaurantId}/reviews")
    @Login
    public ResponseEntity<BaseResponse> update(@VerifiedMember final UserPayload userPayload,
                                               @PathVariable final Long restaurantId,
                                               @Valid @RequestBody final ReviewRequest updateRequest) {
        reviewService.update(restaurantId, userPayload.getId(), updateRequest);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 단건 별점 삭제
//    @DeleteMapping("/restaurants/{restaurantId}/reviews")
//    @Login
//    public ResponseEntity<BaseResponse> delete(@VerifiedMember final UserPayload userPayload,
//                                               @PathVariable final Long restaurantId) {
//        reviewService.delete(restaurantId, userPayload.getId());
//        return ResponseEntity.ok().body(new BaseResponse(null));
//    }
    @DeleteMapping("/restaurants/{restaurantId}/reviews")
    @Login
    public ResponseEntity<BaseResponse> deleteFromMylist(@VerifiedMember final UserPayload userPayload,
                                               @PathVariable final Long restaurantId) {
        reviewService.deleteFromMylist(restaurantId, userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(null));
    }

    // 리스트 별점 등록 -> 굳이 쓸일 없을듯
//    @PostMapping("/users/me/reviews")
//    @Login
//    public ResponseEntity<BaseResponse> createWithList(@VerifiedMember final UserPayload userPayload,
//                                               @Valid @RequestBody final ReviewListRequest reviewListRequest) {
//        reviewService.saveReviewsWithList(userPayload.getId(), reviewListRequest.getReviews());
//        return ResponseEntity.ok().body(new BaseResponse(null));
//    }
    // 여러 건 별점 삭제
    @DeleteMapping("/users/me/reviews")
    @Login
    public ResponseEntity<BaseResponse> deleteAllByUserIdAndRestaurantIdIn(@VerifiedMember final UserPayload userPayload,
                                                                           @RequestBody final ReviewsDeleteRequest request) {
        reviewService.deleteAllByUserIdAndRestaurantIdIn(request.getIds(), userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    @DeleteMapping("/users/me/reviews/test")
    public ResponseEntity<BaseResponse> deleteAllByUserIdAndRestaurantIdIn(@RequestParam final Long userId,
                                                                           @RequestBody final ReviewsDeleteRequest request) {
        reviewService.deleteAllFromMylistByUserIdAndRestaurantIdIn(request.getIds(), userId);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }

    // 내 리뷰 리스트 불러오기
    @GetMapping("/users/me/mylist")
    @Login
    public ResponseEntity<BaseResponse> findMyList(@VerifiedMember final UserPayload userPayload) {
        List<RestaurantInfoResponse> responses = reviewService.findByUserAndIsShownTrue(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(responses));
    }
    // TODO: 친구의 리뷰 리스트 불러오기
    @GetMapping("/users/me/follows-list")
    @Login
    public ResponseEntity<BaseResponse> findFollowsList(@VerifiedMember final UserPayload userPayload) {
        List<FollowsRestaurantsResponse> followsRestaurantsResponseList = reviewService.findByFollowsAndIsShownTrue(userPayload.getId());

        return ResponseEntity.ok().body(new BaseResponse(followsRestaurantsResponseList));
    }
//    @GetMapping("/users/me/follows-list/test")
//    public ResponseEntity<BaseResponse> findFollowsList(@RequestParam final Long userId) {
//        List<FollowsRestaurantsResponse> followsRestaurantsResponseList = reviewService.findByFollowsAndIsShownTrue(userId);
//
//        return ResponseEntity.ok().body(new BaseResponse(followsRestaurantsResponseList));
//    }

}
