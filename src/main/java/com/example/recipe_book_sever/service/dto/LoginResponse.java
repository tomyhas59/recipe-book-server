package com.example.recipe_book_sever.service.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String token;
    private Long id;
    private String email;
    private String nickname;

    public LoginResponse(String token, Long id, String email, String nickname) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }
}