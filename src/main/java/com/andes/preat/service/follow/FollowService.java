package com.andes.preat.service.follow;

import com.andes.preat.domain.follow.Follow;
import com.andes.preat.domain.follow.FollowRepository;
import com.andes.preat.domain.user.User;
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
        User foundFollowerUser = userRepository.findById(followerId)
                .orElseThrow(() -> new NotFollowingException());
        User foundFollowingUser = userRepository.findById(followingId)
                .orElseThrow(() -> new NotFollowingException());
        validateNotFollowing(foundFollowerUser, foundFollowingUser);
        Follow newFollow = Follow.newInstance(foundFollowerUser, foundFollowingUser);
        followRepository.save(newFollow);
    }

    private void validateNotFollowing(final User follower, final User following) {
        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            throw new AlreadyFollowingException();
        }
    }
    @Transactional
    public void unfollow(final Long followerId, final Long followingId) {
        User foundFollowerUser = userRepository.findById(followerId)
                .orElseThrow(() -> new NotFollowingException());
        User foundFollowingUser = userRepository.findById(followingId)
                .orElseThrow(() -> new NotFollowingException());
        final Follow following = findFollowingRelation(foundFollowerUser, foundFollowingUser);
        followRepository.delete(following);
    }

    private Follow findFollowingRelation(final User follower, final User following) {
        return followRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(NotFollowingException::new);
    }
}
