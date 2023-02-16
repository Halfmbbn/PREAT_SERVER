package com.andes.preat.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName(value = "회원가입")
    void 회원가입() {
        // given
        final User user1 = User.newInstance("haha@abc.com", "haha", "male", "20~29", "123456");
        // when
        User savedUser = userRepository.save(user1);
        // then
        Assertions.assertEquals(user1.getEmail(), savedUser.getEmail());
    }
}