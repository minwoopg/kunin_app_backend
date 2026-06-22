package com.kunin.backend.auth;

import com.kunin.backend.auth.dto.*;
import com.kunin.backend.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "회원가입 / 로그인 / 내 정보 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /** POST /api/auth/signup */
    @Operation(summary = "회원가입")
    @PostMapping("/auth/signup")
    public ApiResponse<UserResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ApiResponse.success(authService.signup(request), "회원가입이 완료되었습니다.");
    }

    /** POST /api/auth/login */
    @Operation(summary = "로그인 (JWT 발급)")
    @PostMapping("/auth/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }

    /** GET /api/users/me */
    @Operation(summary = "내 정보 조회", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/users/me")
    public ApiResponse<UserResponse> getMyInfo(@AuthenticationPrincipal String email) {
        return ApiResponse.success(authService.getMyInfo(email));
    }
}
