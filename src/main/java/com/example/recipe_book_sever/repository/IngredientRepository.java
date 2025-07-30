package com.example.recipe_book_sever.repository;

import com.example.recipe_book_sever.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
