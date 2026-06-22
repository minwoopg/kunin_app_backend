package com.kunin.backend.auth.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final String accessToken;
    private final String tokenType = "Bearer";
    private final String email;
    private final String name;
    private final String role;

    public LoginResponse(String accessToken, String email, String name, String role) {
        this.accessToken = accessToken;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
