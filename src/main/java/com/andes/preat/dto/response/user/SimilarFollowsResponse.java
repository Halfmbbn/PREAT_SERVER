package com.andes.preat.dto.response.user;

import com.andes.preat.domain.category.Category;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SimilarFollowsResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private Integer score;

    public SimilarFollowsResponse(Long id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.imageUrl = null;
        this.score = score;
    }
    public static SimilarFollowsResponse from(Category category) {
        return new SimilarFollowsResponse(category.getId(), category.getName(), null, null);
    }
}
