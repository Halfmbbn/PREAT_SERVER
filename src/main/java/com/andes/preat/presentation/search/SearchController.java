package com.andes.preat.presentation.search;

import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.search.SearchResponse;
import com.andes.preat.dto.response.user.LoggedInUserInfoResponse;
import com.andes.preat.service.hateFood.HateFoodService;
import com.andes.preat.service.restaurant.RestaurantService;
import com.andes.preat.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class SearchController {
    private final UserService userService;
    private final HateFoodService hateFoodService;
    private final RestaurantService restaurantService;
    // 유저 닉네임 검색
    @GetMapping("/search/users")
    public ResponseEntity<BaseResponse> getUserByNickname(@RequestParam final String keyword) {
        LoggedInUserInfoResponse foundUser = userService.getUserByNickname(keyword);
        return ResponseEntity.ok().body(new BaseResponse(foundUser));
    }
    // 싫어하는 음식 검색
    @GetMapping("/search/hatefoods")
    public ResponseEntity<BaseResponse> searchHateFoods(@RequestParam final String keyword) {
        SearchResponse searchResponse = hateFoodService.findByFoodContaining(keyword);
        return ResponseEntity.ok().body(new BaseResponse(searchResponse));
    }
    // 식당 검색
    @GetMapping("/search/restaurants")
    public ResponseEntity<BaseResponse> searchRestaurants(@RequestParam final String keyword) {
        SearchResponse searchResponse = restaurantService.findByRestaurantNameContaining(keyword);
        return ResponseEntity.ok().body(new BaseResponse(searchResponse));
    }
}
