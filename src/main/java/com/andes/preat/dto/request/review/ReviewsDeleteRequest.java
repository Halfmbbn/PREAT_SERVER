package com.andes.preat.dto.request.review;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReviewsDeleteRequest {
    private List<Long> restaurantId;
}
