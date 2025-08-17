package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Favorite;
import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserAndRecipe(User user, Recipe recipe);
    List<Favorite> findByUser(User user);

    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
}
