package com.andes.preat.domain.hatefood.userhatefood;

import com.andes.preat.domain.user.User;

import java.util.List;

public interface UserHateFoodRepositoryCustom {
    List<UserHateFood> findAllByUser(User user);

    Long deleteAllByUserId(Long userId);
}
