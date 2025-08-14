package com.example.recipe_book_sever.controller;

import com.example.recipe_book_sever.model.Favorite;
import com.example.recipe_book_sever.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoriteService.addFavorite(favorite);
    }

    @DeleteMapping("/{id}")
    public void removeFavorite(@PathVariable Long id) {
        favoriteService.removeFavorite(id);
    }

    @GetMapping("/check")
    public boolean isFavorited(@RequestParam Long userId, @RequestParam Long recipeId) {
        return favoriteService.isFavorited(userId, recipeId);
    }
}
