package com.andes.preat.dto.response.user;

import com.andes.preat.domain.category.Category;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class HighScoredCategoryResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private Double score;

    public HighScoredCategoryResponse(Long id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.imageUrl = null;
        this.score = score;
    }
    public static HighScoredCategoryResponse from(Category category) {
        return new HighScoredCategoryResponse(category.getId(), category.getName(), null, null);
    }
}
