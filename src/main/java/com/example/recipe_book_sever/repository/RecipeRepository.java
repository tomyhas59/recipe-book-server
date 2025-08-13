package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    Page<Recipe> findByCreatorId(String CreatorId, Pageable pageable);
}