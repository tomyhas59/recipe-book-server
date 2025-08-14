package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCreatorId(Long creatorId);
    List<Recipe> findByCategory(String category);
}
