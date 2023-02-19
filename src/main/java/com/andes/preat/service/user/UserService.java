package com.andes.preat.service.user;

import com.andes.preat.domain.follow.FollowRepository;
import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // TODO : 내정보 확인하기
    @Transactional
    public void saveUserForTest() {
        User newUser1 = User.newInstance("abc@email.com", "user1", "male", "20~29", "1111");
        userRepository.save(newUser1);
        System.out.println("newUser1.getId() = " + newUser1.getId());
        User newUser2 = User.newInstance("abc2@email.com", "user2", "male", "20~29", "1111");
        userRepository.save(newUser2);
        System.out.println("newUser2.getId() = " + newUser2.getId());
        User newUser3 = User.newInstance("abc3@email.com", "user3", "male", "20~29", "1111");
        userRepository.save(newUser3);
        System.out.println("newUser3.getId() = " + newUser3.getId());
    }
}
