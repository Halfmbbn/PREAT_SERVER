package com.andes.preat.dto.response.user;

import com.andes.preat.dto.response.restaurant.RestaurantInfoResponse;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowsRestaurantsResponse {
    private Long id;
    private String nickname;
    private String profileImgUrl;
    private RestaurantInfoResponse rating;
}
