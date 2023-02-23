package com.andes.preat.dto.response.user;

import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowsRestaurantsResponse {
    private Long id;
    private String nickname;
    private String imageUrl;
    private RestaurantInfoResponse restaurant;

    public static FollowsRestaurantsResponse from(User foundUser, RestaurantInfoResponse restaurant) {
        return new FollowsRestaurantsResponse(foundUser.getId(), foundUser.getNickname(), foundUser.getProfileImgUrl(), restaurant);
    }
}
