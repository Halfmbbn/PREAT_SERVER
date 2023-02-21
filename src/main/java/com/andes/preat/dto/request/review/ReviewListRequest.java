package com.andes.preat.dto.request.review;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReviewListRequest {
    @Valid private List<ReviewWithRestaurantIdRequest> reviews;
}
