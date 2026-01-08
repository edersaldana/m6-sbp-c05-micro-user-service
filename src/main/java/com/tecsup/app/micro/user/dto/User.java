package com.tecsup.app.micro.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecsup.app.micro.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Role role;
}
