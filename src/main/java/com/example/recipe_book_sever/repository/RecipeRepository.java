package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findByCreatorId(String CreatorId);
}
