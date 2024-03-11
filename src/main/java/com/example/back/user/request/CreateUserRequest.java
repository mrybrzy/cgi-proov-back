package com.example.back.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreateUserRequest {
    private String username;
    private String name;
    private String password;
    private String passwordRepeated;
}
