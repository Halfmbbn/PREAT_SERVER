package com.andes.preat.domain.hatefood;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HateFoodRepository extends JpaRepository<HateFood, Long> {
}
