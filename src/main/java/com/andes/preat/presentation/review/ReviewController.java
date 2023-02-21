package com.andes.preat.presentation.review;

import com.andes.preat.dto.request.review.ReviewRequest;
import com.andes.preat.dto.request.review.ReviewListRequest;
import com.andes.preat.dto.request.review.ReviewsDeleteRequest;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ReviewController {
    private final ReviewService reviewService;
    // 단건 별점 등록
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<BaseResponse> create(final Long userId,
                                               @PathVariable final Long restaurantId,
                                               @Valid @RequestBody final ReviewRequest reviewRequest) {
        final Long reviewId = reviewService.saveReview(restaurantId, userId, reviewRequest);
        return ResponseEntity.ok().body(new BaseResponse(reviewId));
    }
    // 단건 별점 수정 -> reviewId로 요청이 가는게 아니라 식당 Id일 것 같은디
    @PatchMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<BaseResponse> update(final Long userId,
                                               @PathVariable final Long restaurantId,
                                               @Valid @RequestBody final ReviewRequest updateRequest) {
        reviewService.update(restaurantId, userId, updateRequest);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 단건 별점 삭제
    @DeleteMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<BaseResponse> delete(final Long userId,
                                               @PathVariable final Long restaurantId) {
        reviewService.delete(restaurantId, userId);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 리스트 별점 등록 -> 굳이 쓸일 없을듯
    @PostMapping("/users/me/reviews")
    public ResponseEntity<BaseResponse> createWithList(final Long userId,
                                               @Valid @RequestBody final ReviewListRequest reviewListRequest) {
        reviewService.saveReviewsWithList(userId, reviewListRequest.getReviews());
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 여러 건 별점 삭제
    @DeleteMapping("/users/me/reviews")
    public ResponseEntity<BaseResponse> deleteAllByUserIdAndRestaurantIdIn(final Long userId,
                                                                           @RequestBody final ReviewsDeleteRequest request) {
        reviewService.deleteAllByUserIdAndRestaurantIdIn(request.getRestaurantId(), userId);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 내 리뷰 리스트 불러오기
    // 친구의 리뷰 리스트 불러오기


}
