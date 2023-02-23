package com.andes.preat.dto.response.restaurant;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.review.Review;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantInfoResponse {
    private Long id;
    private String name;
    private String address;
    private String imageUrl;
    private String category;
    private Double latitude;
    private Double longitude;
    private RestaurantRatingResponse rating;
    // review가 존재
    public static RestaurantInfoResponse from(Restaurant restaurant, Review review) {
        return new RestaurantInfoResponse(restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getRestaurantImgUrl(),
                restaurant.getCategory().getName(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                RestaurantRatingResponse.from(false, review.getRating()));
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
                RestaurantRatingResponse.from(true, predictRating));
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
                null);
    }
}
