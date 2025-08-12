package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public Page<Recipe> getRecentRecipes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
return recipeService.getRecentRecipes(page,size);
    }


    @PostMapping
    public Recipe createRecipe(@RequestParam Recipe recipe, @RequestParam String userId){
        return recipeService.createRecipe(recipe, userId);
    }

    @GetMapping
    public Optional<Recipe> getRecipe(@RequestParam Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId,
                                           @RequestParam String userId) throws AccessDeniedException {
        recipeService.deleteRecipe(recipeId, userId);
        return ResponseEntity.noContent().build();
    }
}


