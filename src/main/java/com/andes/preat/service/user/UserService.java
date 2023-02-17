package com.andes.preat.service.user;

import com.andes.preat.domain.user.User;
import com.andes.preat.domain.user.UserRepository;
import com.andes.preat.dto.response.auth.LoginUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
//    @Transactional
//    public LoginUserResponse registerUser(createUserDto request) {
//        User newUser = User.newInstance(request.getNickname());
//        userRepository.save(newUser);
//        return LoginUserResponse.from(newUser);
//    }
}
