package com.andes.preat.domain.hatefood.userhatefood;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.domain.hatefood.HateFood;
import com.andes.preat.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// TODO: 싫어하는 음식 결과 가져오기
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserHateFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hate_food_id")
    private HateFood hateFood;

    private UserHateFood(User user, HateFood hateFood) {
        this.user = user;
        this.hateFood = hateFood;
    }

    public static UserHateFood newInstance(final User user, final HateFood hateFood) {
        return new UserHateFood(user, hateFood);
    }
}
