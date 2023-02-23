package com.andes.preat.dto.response.user;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserStaticsResponse {
    private LoggedInUserInfoResponse userInfo;
    private List<MostVisitedCategoryResponse> mostVisted;
    private List<HighScoredCategoryResponse> highScored;
    private List<SimilarFollowsResponse> similar;

    public static UserStaticsResponse from(LoggedInUserInfoResponse userInfo, List<MostVisitedCategoryResponse> mostVist, List<HighScoredCategoryResponse> highScored, List<SimilarFollowsResponse> similar) {
        return new UserStaticsResponse(userInfo, mostVist, highScored, similar);
    }
}
