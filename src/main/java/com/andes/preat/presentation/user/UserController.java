package com.andes.preat.presentation.user;

import com.andes.preat.dto.request.user.ModifyNicknameRequest;
import com.andes.preat.dto.request.user.UpdateUserHateFoods;
import com.andes.preat.dto.response.auth.NicknameCheckResponse;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.user.*;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.presentation.auth.VerifiedMember;
import com.andes.preat.service.auth.jwt.UserPayload;
import com.andes.preat.service.follow.FollowService;
import com.andes.preat.service.user.UserService;
import com.andes.preat.service.userhatefood.UserHateFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    private final UserHateFoodService userHateFoodService;

    @GetMapping("/usermakefortest")
    public ResponseEntity<BaseResponse> saveUserForTest() {
        userService.saveUserForTest();
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
    @PatchMapping("/me")
    @Login
    public ResponseEntity<BaseResponse> updateLoggedInUserNickname(@VerifiedMember final UserPayload userPayload,
                                                            @RequestBody ModifyNicknameRequest request) {
        LoggedInUserInfoResponse loggedInUserInfo = userService.updateLoggedInUserNickname(userPayload.getId(), request.getNickname());
        return ResponseEntity.ok().body(new BaseResponse(loggedInUserInfo));
    }
    @GetMapping("/me/follows")
    @Login
    public ResponseEntity<BaseResponse> getLoggedInUserFollows(@VerifiedMember final UserPayload userPayload) {
        FollowsInfoResponse userFollows = userService.getUserFollows(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(userFollows));
    }
    @GetMapping("/me/dislikes")
    @Login
    public ResponseEntity<BaseResponse> getUserHateFoods(@VerifiedMember final UserPayload userPayload) {
        List<HateFoodsResponse> userHateFoods = userHateFoodService.getUserHateFoods(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(userHateFoods));
    }
    @PostMapping("/me/dislikes")
    @Login
    public ResponseEntity<BaseResponse> updateUserHateFoods(@VerifiedMember final UserPayload userPayload,
                                                            @RequestBody final UpdateUserHateFoods request) {
        userHateFoodService.updateUserHateFoods(userPayload.getId(), request.getHateFoods());
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
    @GetMapping("/me")
    public ResponseEntity<BaseResponse> getLoggedInUserInfo(@VerifiedMember final UserPayload userPayload) {
        UserStaticsResponse userInfo = userService.getLoggedInUserInfo(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(userInfo));
    }
    @GetMapping("/me/most-visited")
    public ResponseEntity<BaseResponse> getMostVisited(@VerifiedMember final UserPayload userPayload) {
        List<MostVisitedCategoryResponse> userMostCategory = userService.findByUserMostCategory(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(userMostCategory));
    }
    @GetMapping("/me/high-scored")
    public ResponseEntity<BaseResponse> getHighScored(@VerifiedMember final UserPayload userPayload) {
        List<HighScoredCategoryResponse> highScoredCategory = userService.findByUserHighScoredCategory(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(highScoredCategory));
    }
    @GetMapping("/me/similar")
    public ResponseEntity<BaseResponse> getSimilar(@VerifiedMember final UserPayload userPayload) {
        List<SimilarFollowsResponse> similarByUser = userService.findSimilarByUser(userPayload.getId());
        return ResponseEntity.ok().body(new BaseResponse(similarByUser));
    }
}
