package com.andes.preat.domain.follow;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.domain.user.User;
import com.andes.preat.exception.badRequest.SelfFollowException;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {@UniqueConstraint(name = "follow_following_id_follower_id_unique",
                columnNames = {"following_id", "follower_id"})})
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    private Follow(User follower, User following) {
        validateNotSelfFollow(follower, following);
        this.follower = follower;
        this.following = following;
    }

    public static Follow newInstance(User follower, User following) {
        return new Follow(follower, following);
    }

    private void validateNotSelfFollow(final User follower, final User following) {
        if (validateBothNotNull(follower, following) && follower.equals(following)) {
            throw new SelfFollowException();
        }
    }
    private boolean validateBothNotNull(final User follower, final User following) {
        return follower != null && following != null;
    }

}
