package com.andes.preat.dto.response.search;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchResponse {
    private Boolean hasResults;
    private List<?> results;

    public static SearchResponse from(List<?> results) {
        if (results.isEmpty()) {
            return new SearchResponse(false, results);
        }
        return new SearchResponse(true, results);
    }
}
