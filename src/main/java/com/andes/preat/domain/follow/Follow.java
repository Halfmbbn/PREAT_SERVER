package com.andes.preat.domain.follow;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.exception.badRequest.SelfFollowException;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(name = "follow_following_id_follower_id_unique",
                columnNames = {"following_id", "follower_id"})})
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "follower_id")
    private Long followerId;
    @Column(name = "following_id")
    private Long followingId;

    public Follow(Long id, Long followerId, Long followingId) {
        validateNotSelfFollow(followerId, followingId);
        this.id = id;
        this.followerId = followerId;
        this.followingId = followingId;
    }

    private void validateNotSelfFollow(final Long followerId, final Long followingId) {
        if (validateBothNotNull(followerId, followingId) && followerId.equals(followingId)) {
            throw new SelfFollowException();
        }
    }
    private boolean validateBothNotNull(final Long followerId, final Long followingId) {
        return followerId != null && followingId != null;
    }

}
