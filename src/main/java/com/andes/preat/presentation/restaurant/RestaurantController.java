package com.andes.preat.presentation.restaurant;

import com.andes.preat.domain.restaurant.RestaurantRepository;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import com.andes.preat.service.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @GetMapping("")
    public ResponseEntity<BaseResponse> getRestaurants() {
        List<RestaurantInfoResponse> response = restaurantService.findAll();
        return ResponseEntity.ok().body(new BaseResponse(response));
    }
}
