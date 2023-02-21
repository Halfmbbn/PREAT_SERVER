package com.andes.preat.domain.userwish;

import com.andes.preat.domain.common.BaseEntity;
import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_WISHES")
public class UserWish extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private UserWish(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public static UserWish from(User user, Restaurant restaurant) {
        return new UserWish(user, restaurant);
    }

    public boolean isWrittenBy(User user) {
        return this.user.equals(user);
    }

}
