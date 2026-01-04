package com.tecsup.app.micro.user.auth;

import com.tecsup.app.micro.user.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private User user;
}
