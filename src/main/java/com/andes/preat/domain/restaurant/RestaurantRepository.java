package com.andes.preat.domain.restaurant;

import com.andes.preat.domain.hatefood.HateFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Override
    Optional<Restaurant> findById(Long restaurantId);

    List<Restaurant> findByNameContaining(String keyword);
    List<Restaurant> findTop10By();
}
