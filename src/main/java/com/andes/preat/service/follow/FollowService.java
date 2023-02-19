package com.andes.preat.service.follow;

import com.andes.preat.domain.follow.Follow;
import com.andes.preat.domain.follow.FollowRepository;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.exception.badRequest.AlreadyFollowingException;
import com.andes.preat.exception.badRequest.NotFollowingException;
import com.andes.preat.exception.badRequest.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    @Transactional
    public void follow(final Long followerId, final Long followingId) {
        validateMemberExists(followerId);
        validateMemberExists(followingId);
        validateNotFollowing(followerId, followingId);
        final Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();
        followRepository.save(follow);
        System.out.println("follow = " + follow);
//        memberRepository.increaseFollowerCount(followingId);
    }

    private void validateNotFollowing(final Long followerId, final Long followingId) {
        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new AlreadyFollowingException();
        }
    }
    private void validateMemberExists(final Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundUserException();
        }
    }
    @Transactional
    public void unfollow(final Long followerId, final Long followingId) {
        validateMemberExists(followerId);
        validateMemberExists(followingId);
        final Follow following = findFollowingRelation(followerId, followingId);
        followRepository.delete(following);
//        memberRepository.decreaseFollowerCount(followingId);
    }

    private Follow findFollowingRelation(final Long followerId, final Long followingId) {
        return followRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(NotFollowingException::new);
    }
}
