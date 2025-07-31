package com.example.recipe_book_sever.service;

import com.example.recipe_book_sever.model.Recipe;
import com.example.recipe_book_sever.model.User;
import com.example.recipe_book_sever.repository.RecipeRepository;
import com.example.recipe_book_sever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        recipe.getIngredients().forEach(i->i.setRecipe(recipe));

        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id){
        return recipeRepository.findById(id);
    }

    public void deleteRecipe(Long id){
         recipeRepository.deleteById((id));
    }

    @Transactional
    public Recipe updateRecipe(Long id, Recipe newRecipe){
        Recipe recipe=recipeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("레시피를 찾을 수 없습니다."));

        recipe.setName(newRecipe.getName());
        recipe.setDescription(newRecipe.getDescription());
        recipe.setCategory(newRecipe.getCategory());
        recipe.setImage(newRecipe.getImage());

        recipe.getIngredients().clear();
        recipe.getInstructions().clear();

        newRecipe.getIngredients().forEach(i->{
            i.setRecipe(recipe);
            recipe.getIngredients().add(i);
        });

        newRecipe.getInstructions().forEach(i->{
            i.setRecipe(recipe);
            recipe.getInstructions().add(i);
        });

        return recipe;
    }

}
