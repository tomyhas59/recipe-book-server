package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.Favorite;
import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.FavoriteRepository;
import com.example.recipe_book_sever.repository.RecipeRepository;
import com.example.recipe_book_sever.repository.UserRepository;
import com.example.recipe_book_sever.service.dto.FavoriteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
  private  final UserRepository userRepository;
  private  final RecipeRepository recipeRepository;
  
 public String toggleFavorite(Long userId, Long recipeId){
     User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("유저가 없습니다"));
     Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException(("레시피가 없습니다")));

     return favoriteRepository.findByUserAndRecipe(user,recipe)
             .map(favorite->{
                 favoriteRepository.delete(favorite);
                 return "Favorite removed";
             }).orElseGet(()->{
                 Favorite favorite= new Favorite();
                 favorite.setUser(user);
                 favorite.setRecipe(recipe);
                 favoriteRepository.save(favorite);
                 return "Favorite added";
             });
 }

 public List<FavoriteDTO> getFavorites(Long userId){
     User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("유저가 없습니다"));
     List<Favorite> favorites=favoriteRepository.findByUser(user);

     return favorites.stream()
             .map(fav -> new FavoriteDTO(
                     fav.getId(),
                     new FavoriteDTO.RecipeDTO(
                             fav.getRecipe().getId(),
                             fav.getRecipe().getName(),
                             fav.getRecipe().getDescription(),
                             fav.getRecipe().getCategory(),
                             fav.getRecipe().getImage(),
                             fav.getRecipe().getContent(),
                             fav.getRecipe().getCreator() != null ? fav.getRecipe().getCreator().getId() : null // creatorId
                     )
             ))
             .collect(Collectors.toList());
 }

    public boolean isFavorite(Long userId, Long recipeId) {
        return favoriteRepository.existsByUserIdAndRecipeId(userId, recipeId);
    }
}
