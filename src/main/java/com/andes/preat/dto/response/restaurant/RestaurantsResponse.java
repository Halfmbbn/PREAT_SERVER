package com.andes.preat.dto.response.restaurant;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantsResponse {
    private List<RestaurantInfoResponse> restaurants;

    public static RestaurantsResponse from(List<RestaurantInfoResponse> restaurants) {
        return new RestaurantsResponse(restaurants);
    }
}
