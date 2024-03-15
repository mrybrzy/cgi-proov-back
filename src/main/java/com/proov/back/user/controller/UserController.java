package com.proov.back.user.controller;

import com.proov.back.movie.service.MovieService;
import com.proov.back.user.dto.UserDto;
import com.proov.back.user.request.CreateUserRequest;
import com.proov.back.user.request.LoginUserRequest;
import com.proov.back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
