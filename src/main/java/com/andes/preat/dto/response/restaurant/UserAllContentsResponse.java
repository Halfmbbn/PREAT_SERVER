package com.andes.preat.dto.response.restaurant;

import com.andes.preat.dto.response.user.FollowsRestaurantsResponse;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAllContentsResponse {
    private List<RestaurantInfoResponse> mylist;
    private List<FollowsRestaurantsResponse> follows;
    private List<RestaurantInfoResponse> wishes;

    public static UserAllContentsResponse from(List<RestaurantInfoResponse> mylist, List<FollowsRestaurantsResponse> follows, List<RestaurantInfoResponse> wishes) {
        return new UserAllContentsResponse(mylist, follows, wishes);
    }
}
