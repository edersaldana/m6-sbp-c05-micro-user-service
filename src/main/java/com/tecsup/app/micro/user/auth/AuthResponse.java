package com.tecsup.app.micro.user.auth;

import com.tecsup.app.micro.user.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private User user;

}
