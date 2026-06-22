package com.kunin.backend.auth.dto;

import com.kunin.backend.user.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String email;
    private final String name;
    private final String phone;
    private final String address;
    private final String gender;
    private final String role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.gender = user.getGender().name();
        this.role = user.getRole().name();
    }
}
