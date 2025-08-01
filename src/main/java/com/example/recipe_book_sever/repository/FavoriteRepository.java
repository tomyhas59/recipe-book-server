package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findByUserId(String userId);
}
