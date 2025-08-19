package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe,@RequestParam Long userId) {

        System.out.println(userId);
        System.out.println(recipe);
        return recipeService.createRecipe(userId,recipe);
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/creator/{creatorId}")
    public List<Recipe> getRecipesByCreator(@PathVariable Long creatorId) {
        return recipeService.getRecipesByCreator(creatorId);
    }

    @GetMapping("/category/{category}")
    public List<Recipe> getRecipesByCategory(@PathVariable String category) {
        return recipeService.getRecipesByCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
