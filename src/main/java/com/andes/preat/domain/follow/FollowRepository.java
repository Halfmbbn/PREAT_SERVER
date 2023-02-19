package com.andes.preat.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerIdAndFollowingId(final Long followerId, final Long followingId);

    Optional<Follow> findByFollowerIdAndFollowingId(final Long followerId, final Long followingId);
}
