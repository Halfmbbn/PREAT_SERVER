package com.andes.preat.presentation.user;

import com.andes.preat.dto.request.user.ModifyNicknameRequest;
import com.andes.preat.dto.request.user.UpdateUserHateFoods;
import com.andes.preat.dto.response.auth.NicknameCheckResponse;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.hatefood.HateFoodsResponse;
import com.andes.preat.dto.response.user.FollowsInfoResponse;
import com.andes.preat.dto.response.user.LoggedInUserInfoResponse;
import com.andes.preat.presentation.auth.Login;
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
    private final FollowService followService;
    private final UserHateFoodService userHateFoodService;

    @GetMapping("/usermakefortest")
    public ResponseEntity<BaseResponse> saveUserForTest() {
        userService.saveUserForTest();
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
    @GetMapping("/me")
    public ResponseEntity<BaseResponse> getLoggedInUserInfo(final Long userId) {
        LoggedInUserInfoResponse loggedInUserInfo = userService.getLoggedInUserInfo(userId);
        return ResponseEntity.ok().body(new BaseResponse(loggedInUserInfo));
    }
    @PatchMapping("/me")
    public ResponseEntity<BaseResponse> updateLoggedInUserNickname(final Long userId,
                                                            @RequestBody ModifyNicknameRequest request) {
        LoggedInUserInfoResponse loggedInUserInfo = userService.updateLoggedInUserNickname(userId, request.getNickname());
        return ResponseEntity.ok().body(new BaseResponse(loggedInUserInfo));
    }
    @GetMapping("/me/follows")
    public ResponseEntity<BaseResponse> getLoggedInUserFollows(final Long userId) {
        FollowsInfoResponse userFollows = userService.getUserFollows(userId);
        return ResponseEntity.ok().body(new BaseResponse(userFollows));
    }
    @GetMapping("/me/hatefoods")
    public ResponseEntity<BaseResponse> getUserHateFoods(final Long userId) {
        List<HateFoodsResponse> userHateFoods = userHateFoodService.getUserHateFoods(userId);
        return ResponseEntity.ok().body(new BaseResponse(userHateFoods));
    }
    @PostMapping("/me/hatefoods")
    public ResponseEntity<BaseResponse> updateUserHateFoods(final Long userId,
                                                            @RequestBody final UpdateUserHateFoods request) {
        userHateFoodService.updateUserHateFoods(userId, request.getHateFoods());
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }


}
