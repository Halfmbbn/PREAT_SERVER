package com.andes.preat.dto.response.user;

import com.andes.preat.domain.category.Category;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MostVisitedCategoryResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private Long score;

    public MostVisitedCategoryResponse(Long id, String name, Long score) {
        this.id = id;
        this.name = name;
        this.imageUrl = null;
        this.score = score;
    }
    public static MostVisitedCategoryResponse from(Category category) {
        return new MostVisitedCategoryResponse(category.getId(), category.getName(), null, null);
    }
}
