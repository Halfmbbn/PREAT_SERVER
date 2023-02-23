package com.andes.preat.dto.response.restaurant;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantRatingResponse {
    private Boolean hasPredict;
    private Double value;

    public static RestaurantRatingResponse from(Boolean hasPredict, Double value) {
        return new RestaurantRatingResponse(hasPredict, value);
    }
}
