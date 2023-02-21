package com.andes.preat.presentation.userwish;

import com.andes.preat.dto.request.review.ReviewRequest;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.restaurant.RestaurantsResponse;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.presentation.auth.VerifiedMember;
import com.andes.preat.service.auth.jwt.UserPayload;
import com.andes.preat.service.userwish.UserWishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Login
    public ResponseEntity<BaseResponse> create(@VerifiedMember final UserPayload userPayload,
                                               @PathVariable final Long restaurantId) {
        final Long reviewId = userWishService.saveUserWish(restaurantId, userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(null));
    }

    // 단건 가봐야 할 맛집 삭제
    @DeleteMapping("/restaurants/{restaurantId}/wishes")
    @Login
    public ResponseEntity<BaseResponse> delete(@VerifiedMember final UserPayload userPayload,
                                               @PathVariable final Long restaurantId) {
        userWishService.delete(restaurantId, userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(null));
    }
    // 가봐야 할 맛집 리스트 조회 - 예상별점 어떻게?
    @GetMapping("/users/me/wishes")
    @Login
    public ResponseEntity<BaseResponse> getWishes(@VerifiedMember final UserPayload userPayload) {
        RestaurantsResponse restaurantsResponse = userWishService.findAllByUser(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(restaurantsResponse));
    }
}
