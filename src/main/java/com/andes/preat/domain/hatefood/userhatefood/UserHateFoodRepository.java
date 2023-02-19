package com.andes.preat.domain.hatefood.userhatefood;

import com.andes.preat.domain.hatefood.HateFood;
import com.andes.preat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHateFoodRepository extends JpaRepository<UserHateFood, Long> {
    boolean existsByUserAndHateFood(final User user, final HateFood hateFood);
}
