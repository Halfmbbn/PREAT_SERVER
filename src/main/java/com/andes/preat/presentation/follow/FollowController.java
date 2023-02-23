package com.andes.preat.presentation.follow;

import com.andes.preat.dto.response.common.BaseResponse;
import com.andes.preat.presentation.auth.Login;
import com.andes.preat.presentation.auth.VerifiedMember;
import com.andes.preat.service.auth.jwt.UserPayload;
import com.andes.preat.service.follow.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{followingId}/follow")
    @Login
    public ResponseEntity<BaseResponse> follow(@VerifiedMember UserPayload userPayload,
                                               @RequestParam final Long followingId) {
        followService.follow(userPayload.getId(), followingId);
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
    @PostMapping("/{followingId}/follow/test")
    public ResponseEntity<BaseResponse> followTest(@RequestParam final Long userId,
                                               @RequestParam final Long followingId) {
        followService.follow(userId, followingId);
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
    @PostMapping("/{followingId}/unfollow")
    @Login
    public ResponseEntity<BaseResponse> unfollow(@VerifiedMember UserPayload userPayload,
                                                 @RequestParam final Long followingId) {
        followService.unfollow(userPayload.getId(), followingId);
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
}
