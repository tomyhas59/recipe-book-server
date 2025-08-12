package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.Favorite;
import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.FavoriteRepository;
import com.example.recipe_book_sever.repository.RecipeRepository;
import com.example.recipe_book_sever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private  final RecipeRepository recipeRepository;

    @Transactional
    public void addFavorite(String userId, Long recipeId){
        User user= userRepository.findById(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Recipe recipe=recipeRepository.findById(recipeId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (favoriteRepository.existsByUserIdAndRecipeId(userId, recipeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 즐겨찾기한 레시피입니다.");
        }

        Favorite favorite=new Favorite();
        favorite.setUser(user);
        favorite.setRecipe(recipe);
        favoriteRepository.save(favorite);
    }

    @Transactional
    public void removeFavorite(String userId, Long recipeId) {
             Favorite favorite = favoriteRepository.findByUserIdAndRecipeId(userId, recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "즐겨찾기가 존재하지 않습니다."));

        favoriteRepository.delete(favorite);
    }

    public List<Favorite> getUserFavorites(String userId) {
        return favoriteRepository.findByUserId(userId);
    }

}
