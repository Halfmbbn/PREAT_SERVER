package com.andes.preat.domain.review;

import com.andes.preat.domain.category.Category;
import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.user.User;
import com.andes.preat.dto.response.user.CategoryStaticsResponse;
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

    List<Review> findByUserAndIsShownTrueAndRatingGreaterThanEqual(User user, Double rating);
    @Query("select r from Review r join fetch r.restaurant where r.user.id in :userId and r.rating >= 3.0 and r.isShown = true")
    List<Review> findAllByUserIdAndIsShownTrueAndRatingGreaterThanEqual(@Param("userId") List<Long> userId);

//    @Query("select new com.andes.preat.dto.response.user.CategoryStaticsResponse(c.id,c.name,count(c.id)) " +
//            "from Review r join fetch r.restaurant res join fetch res.category c group by c.id order by count(c.id) desc ")
//    List<CategoryStaticsResponse> findMostVisitedCategory(@Param("userId") Long userId);
}
