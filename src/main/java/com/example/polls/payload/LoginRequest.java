package com.example.polls.payload;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    
    @NotEmpty
    private String usernameOrEmail;
    
    @NotEmpty
    private String password;
}
