package com.andes.preat.domain.category;

import com.andes.preat.dto.response.user.HighScoredCategoryResponse;
import com.andes.preat.dto.response.user.MostVisitedCategoryResponse;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<MostVisitedCategoryResponse> findByUserMostCategory(Long userId);
    List<MostVisitedCategoryResponse> findByUserMostCategoryDetail(Long userId);
    List<HighScoredCategoryResponse> findByUserHighScoredCategory(Long userId);
    List<HighScoredCategoryResponse> findByUserHighScoredCategoryDetail(Long userId);
}
