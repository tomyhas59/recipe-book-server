package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.RecipeRepository;
import com.example.recipe_book_sever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Transactional
    public Recipe createRecipe(Recipe recipe, String userId){
        User user =userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("유저를 찾을 수 없습니다."));

        recipe.setCreator(user);

        recipe.getIngredients().forEach(i->i.setRecipe(recipe));
        recipe.getInstructions().forEach(i->i.setRecipe(recipe));

        return recipeRepository.save(recipe);
    }

    public Page<Recipe> getRecentRecipes(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return recipeRepository.findAll(pageable);
    }

    public Page<Recipe> getCreatorRecipes(String userId,int page,int size){
        Pageable pageable =PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"id"));
        return recipeRepository.findByCreatorId(userId, pageable);
    }

    public Optional<Recipe> getRecipeById(Long recipeId){
        return recipeRepository.findById(recipeId);
    }

    public void deleteRecipe(Long recipeId,String userId) throws AccessDeniedException {
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(()->new IllegalArgumentException("삭제할 레시피가 없습니다."));
        if(!recipe.getCreator().getId().equals(userId)){
            throw new AccessDeniedException("본인의 레시피만 삭제할 수 있습니다.");
        }
        recipeRepository.delete(recipe);
    }

    @Transactional
    public Recipe updateRecipe(Long recipeId, String userId, Recipe updatedRecipe) throws AccessDeniedException{
        Recipe recipe=recipeRepository.findById(recipeId).orElseThrow(()->new IllegalArgumentException("레시피를 찾을 수 없습니다."));

        if (!recipe.getCreator().getId().equals(userId)) {
            throw new AccessDeniedException("본인의 레시피만 수정할 수 있습니다.");
        }

        recipe.setName(updatedRecipe.getName());
        recipe.setDescription(updatedRecipe.getDescription());
        recipe.setCategory(updatedRecipe.getCategory());
        recipe.setImage(updatedRecipe.getImage());

        recipe.getIngredients().clear();
        recipe.getInstructions().clear();

        updatedRecipe.getIngredients().forEach(i->{
            i.setRecipe(recipe);
            recipe.getIngredients().add(i);
        });

        updatedRecipe.getInstructions().forEach(i->{
            i.setRecipe(recipe);
            recipe.getInstructions().add(i);
        });

        return recipeRepository.save(recipe);
    }

}
