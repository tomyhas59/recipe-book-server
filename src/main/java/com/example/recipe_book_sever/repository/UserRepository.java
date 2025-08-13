package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
}
