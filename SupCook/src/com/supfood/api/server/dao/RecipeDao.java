package com.supfood.api.server.dao;

import com.supfood.entity.Recipe;
import com.supfood.entity.User;

import java.util.List;

public interface RecipeDao {

    void addRecipe(Recipe recipe);

    void updateRecipe(Recipe recipe);

    void removeRecipeById(int id);

    void removeRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    List<Recipe> getAllRecipes();

    List<Recipe> getUserRecipes(User user);

    List<Recipe> searchRecipes(String query);

    List<Recipe> getRecipesByCategory(int id);

    List<Recipe> getRecipePage(int page, int pageSize);
}