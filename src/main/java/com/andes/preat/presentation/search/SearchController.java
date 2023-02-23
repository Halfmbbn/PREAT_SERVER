package com.andes.preat.presentation.search;

import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import com.andes.preat.dto.response.search.SearchResponse;
import com.andes.preat.dto.response.user.LoggedInUserInfoResponse;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.presentation.auth.VerifiedMember;
import com.andes.preat.service.auth.jwt.UserPayload;
import com.andes.preat.service.hateFood.HateFoodService;
import com.andes.preat.service.restaurant.RestaurantService;
import com.andes.preat.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class SearchController {
    private final UserService userService;
    private final HateFoodService hateFoodService;
    private final RestaurantService restaurantService;
    // 유저 닉네임 검색 -> 검색으로 전환 필요
    @GetMapping("/search/users")
    public ResponseEntity<BaseResponse> getUserByNickname(@RequestParam final String query) {
        LoggedInUserInfoResponse foundUser = userService.getUserByNickname(query);
        return ResponseEntity.ok().body(new BaseResponse(foundUser));
    }
    // 식당 검색
    @GetMapping("/search/restaurants")
    @Login
    public ResponseEntity<BaseResponse> searchRestaurants(@VerifiedMember final UserPayload userPayload,
                                                          @RequestParam final String query) {
        List<RestaurantInfoResponse> responses = restaurantService.findByRestaurantNameContaining(userPayload.getId(), query);
        return ResponseEntity.ok().body(new BaseResponse(responses));
    }
    @GetMapping("/signup/search/restaurants")
    public ResponseEntity<BaseResponse> searchRestaurantsWithNoToken(@RequestParam final String query) {
        List<RestaurantInfoResponse> responses = restaurantService.findByRestaurantNameContainingByNoToken(query);
        return ResponseEntity.ok().body(new BaseResponse(responses));
    }
}
