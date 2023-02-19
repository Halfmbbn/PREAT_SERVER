package com.andes.preat.dto.response.hatefood;

import com.andes.preat.domain.hatefood.HateFood;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HateFoodsResponse {
    private Long id;
    private String food;
    private String imgUrl;

    public static HateFoodsResponse from(final HateFood hateFood) {
        return new HateFoodsResponse(hateFood.getId(), hateFood.getFood(), hateFood.getImgUrl());
    }
}
