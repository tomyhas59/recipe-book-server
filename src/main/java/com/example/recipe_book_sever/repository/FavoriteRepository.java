package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

    boolean existsByUserIdAndRecipeId(String userId, Long recipeId);
    List<Favorite> findByUserId(String userId);

    Optional<Favorite> findByUserIdAndRecipeId(String userId, Long recipeId);

}
