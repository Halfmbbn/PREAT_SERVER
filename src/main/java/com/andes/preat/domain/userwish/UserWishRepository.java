package com.andes.preat.domain.userwish;

import com.andes.preat.domain.restaurant.Restaurant;
import com.andes.preat.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserWishRepository extends JpaRepository<UserWish, Long> {
    boolean existsByUserAndRestaurant(User user, Restaurant restaurant);
    Optional<UserWish> findByUserAndRestaurant(User user, Restaurant restaurant);

    @Modifying(clearAutomatically = true)
    @Query("delete from UserWish u where u.id in :ids")
    void deleteAllByIdInQuery(@Param("ids") List<Long> ids);

//    @Query("select r from Review r join fetch r.member join fetch r.product where r.member.id = :memberId")
//    Slice<Review> findPageByMemberId(Long memberId, Pageable pageable);
    @Query("select u from UserWish u join fetch u.restaurant where u.user = :user")
    List<UserWish> findAllByUser(@Param("user") User user);

    void deleteByUserAndRestaurant(User user, Restaurant restaurant);
}
