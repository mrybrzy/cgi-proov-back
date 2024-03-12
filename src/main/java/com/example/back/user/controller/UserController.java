package com.example.back.user.controller;

import com.example.back.movie.service.MovieService;
import com.example.back.user.dto.UserDto;
import com.example.back.user.request.CreateUserRequest;
import com.example.back.user.request.LoginUserRequest;
import com.example.back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MovieService movieService;
    @PostMapping("/public/register")
    public void registerUser(@RequestBody CreateUserRequest request) {
        userService.registerUser(request);
    }
    @PostMapping("/public/login")
    public String login(@RequestBody LoginUserRequest request) {
        return userService.login(request);
    }
    @GetMapping("/user/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }
    @GetMapping("/recommendation/{username}")
    public void getRecommendation(@PathVariable("username") String username) {
        userService.getRecommendation(username);
    }
}
