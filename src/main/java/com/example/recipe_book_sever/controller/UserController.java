package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User CreateUser(@RequestBody User user){
       return userService.createUser(user);
    }

}

