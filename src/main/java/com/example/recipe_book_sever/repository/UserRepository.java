package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
