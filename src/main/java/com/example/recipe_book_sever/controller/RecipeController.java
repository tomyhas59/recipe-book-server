package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.model.Favorite;
import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.service.FavoriteService;
import com.example.recipe_book_sever.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final FavoriteService favoriteService;
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

    @GetMapping("/{creatorId}/userRecipes")
    public Page<Recipe> getCreatorRecipes(@PathVariable String creatorId,@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){
        return recipeService.getCreatorRecipes(creatorId, page, size);
    }

    @GetMapping("/{recipeId}")
    public Optional<Recipe> getRecipe(@PathVariable Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long recipeId,
                                           @RequestParam String userId) throws AccessDeniedException {
        recipeService.deleteRecipe(recipeId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long recipeId,@RequestParam String userId,@RequestBody Recipe updatedRecipe) throws AccessDeniedException {
     Recipe savedRecipe=  recipeService.updateRecipe(recipeId,userId,updatedRecipe);
        return ResponseEntity.ok(savedRecipe);
    }

    @PostMapping("/{recipeId}/favorite")
    public void addFavorite(@PathVariable Long recipeId,@RequestParam String userId){
favoriteService.addFavorite(recipeId,userId);
    }

    @DeleteMapping("/{recipeId}/favorite")
    public void removeFavorite(@PathVariable Long recipeId, @RequestParam String userId){
        favoriteService.removeFavorite(recipeId,userId);
    }

    @GetMapping("/users/{userId}/favorite")
    public List<Favorite> getUserFavorites(@PathVariable String userId){
      return favoriteService.getUserFavorites(userId);
    }
}


