package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.Favorite;
import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.FavoriteRepository;
import com.example.recipe_book_sever.repository.RecipeRepository;
import com.example.recipe_book_sever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final FavoriteRepository favoriteRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public User createUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일이 이미 존재합니다.");
        }


        if (userRepository.findByNickname(user.getNickname()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "닉네임이 이미 존재합니다.");

        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }



    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }



}
