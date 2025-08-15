package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.RecipeRepository;
import com.example.recipe_book_sever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private  final UserRepository userRepository;
    public Recipe createRecipe(Long userId, Recipe recipe) {

        User user = userRepository.findById(userId).
                orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));;

        recipe.setCreator(user);
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getRecipesByCreator(Long creatorId) {
        return recipeRepository.findByCreatorId(creatorId);
    }

    public List<Recipe> getRecipesByCategory(String category) {
        return recipeRepository.findByCategory(category);
    }

    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
