package com.andes.preat.presentation.user;

import com.andes.preat.dto.request.user.ModifyNicknameRequest;
import com.andes.preat.dto.response.auth.NicknameCheckResponse;
import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.dto.response.user.LoggedInUserInfoResponse;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

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

}
