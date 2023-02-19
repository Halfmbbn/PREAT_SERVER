package com.andes.preat.service.userhatefood;

import com.andes.preat.domain.hatefood.HateFood;
import com.andes.preat.domain.hatefood.HateFoodRepository;
import com.andes.preat.domain.hatefood.userhatefood.UserHateFood;
import com.andes.preat.domain.hatefood.userhatefood.UserHateFoodRepository;
import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.exception.badRequest.NotFoundHateFoodException;
import com.andes.preat.exception.badRequest.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserHateFoodService {
    private final UserHateFoodRepository userHateFoodRepository;
    private final HateFoodRepository hateFoodRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveUserHateFoods(final Long userId, final List<Long> request) {
        List<HateFood> hateFoods = request.stream()
                .map(id -> hateFoodRepository.findById(id)
                        .orElseThrow(() -> new NotFoundHateFoodException()))
                .distinct().collect(Collectors.toList());
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException());
        addUserHateFoods(foundUser, hateFoods);
    }

    private void addUserHateFoods(final User foundUser, final List<HateFood> hateFoods) {
        if (!hateFoods.isEmpty()) {
            for (HateFood hatefood: hateFoods) {
                if (!userHateFoodRepository.existsByUserAndHateFood(foundUser, hatefood)) {
                    UserHateFood newUserHateFood = UserHateFood.newInstance(foundUser, hatefood);
                    userHateFoodRepository.save(newUserHateFood);
                }
            }
        }
    }
    // TODO : 유저별 싫어하는 음식 선택 결과 조회
    // TODO : 싫어하는 음식 저장
    // TODO : 싫어하는 음식 업데이트
}
