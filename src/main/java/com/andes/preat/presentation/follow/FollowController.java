package com.andes.preat.presentation.follow;

import com.andes.preat.dto.response.common.BaseResponse;
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
    public ResponseEntity<BaseResponse> follow(@RequestParam final Long followingId) {
        followService.follow(1L, followingId);
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
    @PostMapping("/{followingId}/unfollow")
    public ResponseEntity<BaseResponse> unfollow(@RequestParam final Long followingId) {
        followService.unfollow(1L, followingId);
        return ResponseEntity.ok().body(new BaseResponse("ok"));
    }
}
