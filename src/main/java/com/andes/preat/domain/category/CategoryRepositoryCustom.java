package com.andes.preat.domain.category;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<Category> findByUserGroupByCategory(Long userId);
}
