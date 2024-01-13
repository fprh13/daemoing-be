package com.daemoing.daemo.service;

import com.daemoing.daemo.domain.User;
import com.daemoing.daemo.domain.type.Gender;
import com.daemoing.daemo.domain.type.Role;
import com.daemoing.daemo.global.common.ErrorCode;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.daemoing.daemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static com.daemoing.daemo.service.dto.UserDto.*;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    /**
     * 아이디 중복 확인
     **/
    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }

    /**
     * CREATE
     */
    @Transactional
    public Long join(JoinDto joinDto) {
        User user = userRepository.save(new User(joinDto.getLoginId(),
                passwordEncoder.encode(joinDto.getPassword()),
                joinDto.getUsername(),
                joinDto.getDescription(),
                joinDto.getEmail(),
                joinDto.getStudentId(),
                Gender.valueOf(joinDto.getGender()),
                joinDto.getUniv(),
                Role.USER));
        return user.getId();
    }

    /**
     * UPDATE
     */
    @Transactional
    public Long update(UpdateDto updateDto, String principal) {
        User user = userRepository.findByLoginId(principal)
                .orElseThrow(() -> new RuntimeException("회원이 없습니다."));
        user.update(updateDto.getLoginId(),
                updateDto.getUsername(),
                updateDto.getDescription(),
                updateDto.getEmail(),
                updateDto.getStudentId(),
                Gender.valueOf(updateDto.getGender()),
                updateDto.getUniv());
        return user.getId();
    }


    /**
     * READ - ME
     */
    public InfoDto info() {
        User user = userRepository.findMyInfoByLoginId(SecurityContextHolder.getContext().getAuthentication().getName());
        return new InfoDto(user.getLoginId(),
                user.getUsername(),
                user.getDescription(),
                user.getEmail(),
                user.getStudentId(),
                user.getGender().toString(),
                user.getUniv());
    }

    /**
     * READ - OTHERS
     */
    public InfoDto othersInfo(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        return new InfoDto(user.getLoginId(),
                user.getUsername(),
                user.getDescription(),
                user.getEmail(),
                user.getStudentId(),
                user.getGender().toString(),
                user.getUniv());
    }

}

