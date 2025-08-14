package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.service.UserService;
import com.example.recipe_book_sever.service.dto.LoginRequest;
import com.example.recipe_book_sever.service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }


}
