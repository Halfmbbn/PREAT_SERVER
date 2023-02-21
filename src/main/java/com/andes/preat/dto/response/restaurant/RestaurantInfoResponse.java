package com.andes.preat.dto.response.restaurant;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.review.Review;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantInfoResponse {
    private Long restaurantId;
    private String restaurantName;
    private String address;
    private String imgUrl;
    private String category;
    private Double latitude;
    private Double longitude;
    private Boolean hasPredict;
    private Double rating;
    private Double predictRating;

    // review가 존재
    public static RestaurantInfoResponse from(Restaurant restaurant, Review review) {
        return new RestaurantInfoResponse(restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getRestaurantImgUrl(),
                restaurant.getCategory().getName(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                false,
                review.getRating(),
                null);
    }
    // 예상별점이 있을 때
    public static RestaurantInfoResponse from(Restaurant restaurant, Double predictRating) {
        return new RestaurantInfoResponse(restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getRestaurantImgUrl(),
                restaurant.getCategory().getName(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                true,
                null,
                predictRating);
    }
    // 아무것도 없을 때
    public static RestaurantInfoResponse from(Restaurant restaurant) {
        return new RestaurantInfoResponse(restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getRestaurantImgUrl(),
                restaurant.getCategory().getName(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                false,
                null,
                null);
    }
}
