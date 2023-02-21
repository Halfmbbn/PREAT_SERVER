package com.andes.preat.domain.review;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserAndRestaurant(User user, Restaurant restaurant);

    Optional<Review> findByUserAndRestaurant(User user, Restaurant restaurant);

    @Modifying(clearAutomatically = true)
    @Query("delete from Review r where r.user.id = :userId and r.restaurant.id in :ids")
    Integer deleteAllByUserIdAndRestaurantIdIn(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
