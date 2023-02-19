package com.andes.preat.domain.follow;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.exception.badRequest.SelfFollowException;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long followerId;
    @Column
    private Long followingId;
//    @Column
//    private boolean isFollowEach;

//    private Follow(Long followerId, Long followingId, boolean isFollowEach) {
//        validateNotSelfFollow(followerId, followingId);
//        this.followerId = followerId;
//        this.followingId = followingId;
//        this.isFollowEach = isFollowEach;
//    }

    private Follow(Long followerId, Long followingId) {
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
