package com.pro.tasker.user;

import com.pro.tasker.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
