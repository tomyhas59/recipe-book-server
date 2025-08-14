package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
}
