package com.andes.preat.domain.follow;

import com.andes.preat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {
    boolean existsByFollowerAndFollowing(final User follower, final User following);

    boolean existsByFollower(final User follower);
    Optional<Follow> findByFollowerAndFollowing(final User follower, final User following);
}
