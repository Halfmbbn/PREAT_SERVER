package com.andes.preat.dto.response.user;

import com.andes.preat.domain.category.Category;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CategoryStaticsResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private Long count;

    public CategoryStaticsResponse(Long id, String name, Long count) {
        this.id = id;
        this.name = name;
        this.imageUrl = null;
        this.count = count;
    }

    public static CategoryStaticsResponse from(Category category) {
        return new CategoryStaticsResponse(category.getId(), category.getName(), null, null);
    }
}
