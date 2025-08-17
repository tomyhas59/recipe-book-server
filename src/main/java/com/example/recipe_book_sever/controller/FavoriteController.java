package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.service.FavoriteService;
import com.example.recipe_book_sever.service.dto.FavoriteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

 @PostMapping("/{userId}/toggle/{recipeId}")
 public String toggleFavorite(@PathVariable Long userId,@PathVariable Long recipeId){
     return favoriteService.toggleFavorite(userId,recipeId);
 }

 @GetMapping("/{userId}")
 public List<FavoriteDTO> getFavorites(@PathVariable Long userId){
     return favoriteService.getFavorites(userId);
 }
    @GetMapping("/isFavorite")
    public boolean isFavorite(@RequestParam Long userId, @RequestParam Long recipeId) {
        return favoriteService.isFavorite(userId, recipeId);
    }
}
