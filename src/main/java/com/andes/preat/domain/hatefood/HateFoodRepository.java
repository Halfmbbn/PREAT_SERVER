package com.andes.preat.domain.hatefood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HateFoodRepository extends JpaRepository<HateFood, Long> {
    List<HateFood> findByFoodContaining(String food);
}
