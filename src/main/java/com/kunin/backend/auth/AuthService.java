package com.kunin.backend.auth;

import com.kunin.backend.auth.dto.*;
import com.kunin.backend.common.CustomException;
import com.kunin.backend.common.ErrorCode;
import com.kunin.backend.config.JwtTokenProvider;
import com.kunin.backend.user.User;
import com.kunin.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /** 회원가입 */
    @Transactional
    public UserResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .gender(request.getGender())
                .build();

        return new UserResponse(userRepository.save(user));
    }

    /** 로그인 */
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole().name());
        return new LoginResponse(token, user.getEmail(), user.getName(), user.getRole().name());
    }

    /** 내 정보 조회 */
    public UserResponse getMyInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponse(user);
    }
}
