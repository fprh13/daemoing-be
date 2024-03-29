package com.daemoing.daemo.global.security;

import com.daemoing.daemo.domain.User;
import com.daemoing.daemo.global.common.ErrorCode;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.daemoing.daemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String loginId) {
        User findUser = userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new CustomException(ErrorCode.INVALID_CREDENTIALS));

        if(findUser != null){
            UserDetailsImpl userDetails = new UserDetailsImpl(findUser);
            return  userDetails;
        }

        return null;
    }

}
