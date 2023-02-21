package com.andes.preat.presentation.userwish;

import com.andes.preat.dto.request.review.ReviewRequest;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.restaurant.RestaurantsResponse;
import com.andes.preat.service.userwish.UserWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserWishController {
    private final UserWishService userWishService;

    // 단건 가봐야 할 맛집 등록
    @PostMapping("/restaurants/{restaurantId}/wishes")
    public ResponseEntity<BaseResponse> create(final Long userId,
                                               @PathVariable final Long restaurantId) {
        final Long reviewId = userWishService.saveUserWish(restaurantId, userId);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }

    // 단건 가봐야 할 맛집 삭제
    @DeleteMapping("/restaurants/{restaurantId}/wishes")
    public ResponseEntity<BaseResponse> delete(final Long userId,
                                               @PathVariable final Long restaurantId) {
        userWishService.delete(restaurantId, userId);
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 가봐야 할 맛집 리스트 조회 - 예상별점 어떻게?
    @GetMapping("/users/me/wishes")
    public ResponseEntity<BaseResponse> getWishes(final Long userId) {
        RestaurantsResponse restaurantsResponse = userWishService.findAllByUser(userId);
        return ResponseEntity.ok().body(new BaseResponse(restaurantsResponse));
    }
}
