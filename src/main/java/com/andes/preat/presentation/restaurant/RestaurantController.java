package com.andes.preat.presentation.restaurant;

import com.andes.preat.domain.restaurant.RestaurantRepository;
import com.andes.preat.domain.userwish.UserWishRepository;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import com.andes.preat.dto.response.restaurant.UserAllContentsResponse;
import com.andes.preat.dto.response.user.FollowsRestaurantsResponse;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.presentation.auth.VerifiedMember;
import com.andes.preat.service.auth.jwt.UserPayload;
import com.andes.preat.service.restaurant.RestaurantService;
import com.andes.preat.service.review.ReviewService;
import com.andes.preat.service.userwish.UserWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;
    private final UserWishService userWishService;

    @GetMapping("/v1/restaurants")
    public ResponseEntity<BaseResponse> getRestaurants() {
        List<RestaurantInfoResponse> response = restaurantService.findAll();
        return ResponseEntity.ok().body(new BaseResponse(response));
    }
    @GetMapping("/v1/home")
    @Login
    public ResponseEntity<BaseResponse> getRestaurants(@VerifiedMember UserPayload userPayload) {
        List<RestaurantInfoResponse> mylist = reviewService.findByUserAndIsShownTrue(userPayload.getId());
        List<FollowsRestaurantsResponse> follows = reviewService.findByFollowsAndIsShownTrue(userPayload.getId());
        List<RestaurantInfoResponse> wishes = userWishService.findAllByUser(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(UserAllContentsResponse.from(mylist, follows, wishes)));
    }
    @GetMapping("/v1/home/test")
    public ResponseEntity<BaseResponse> getRestaurantsTest(@RequestParam Long userId) {
        List<RestaurantInfoResponse> mylist = reviewService.findByUserAndIsShownTrue(userId);
        List<FollowsRestaurantsResponse> follows = reviewService.findByFollowsAndIsShownTrue(userId);
        List<RestaurantInfoResponse> wishes = userWishService.findAllByUser(userId);
        return ResponseEntity.ok().body(new BaseResponse(UserAllContentsResponse.from(mylist, follows, wishes)));
    }
}
