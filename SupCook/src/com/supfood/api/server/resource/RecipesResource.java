package com.supfood.api.server.resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.gson.GsonUtils;
import com.supfood.api.server.dao.DaoFactory;
import com.supfood.api.server.dao.RecipeDao;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Recipe;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Type;
import java.util.List;

@Path("/recipes")
public class RecipesResource {

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createRecipe(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Recipe recipe = gson.fromJson(payload, Recipe.class);
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        try {
            recipeDao.addRecipe(recipe);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        return ApiResponse.getAsJson(true, "success");
    }

    @GET
    public String getAllRecipes() {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        List<Recipe> recipes;
        try {
            recipes = recipeDao.getAllRecipes();
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        String json = gson.toJson(recipes, type);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/page/{page}/{pageSize}")
    public String getPageRecipe(@PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        List<Recipe> recipes;
        try {
            recipes = recipeDao.getRecipePage(page, pageSize);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        String json = gson.toJson(recipes, type);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/{id}")
    public String getRecipe(@PathParam("id") int id) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        Recipe recipe;
        try {
            recipe = recipeDao.getRecipe(id);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        String json = gson.toJson(recipe);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/category/{id}")
    public String getCategoryRecipes(@PathParam("id") int id) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        List<Recipe> recipes;
        try {
            recipes = recipeDao.getRecipesByCategory(id);
        } catch (Exception e) {
            return ApiResponse.getAsJson(false,"DB_ERROR");
        }
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        String json = gson.toJson(recipes, type);
        return ApiResponse.getAsJson(true, json);
    }

    @GET
    @Path("/search/{query}")
    public String searchRecipe(@PathParam("query") String query) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        List<Recipe> recipes = recipeDao.searchRecipes(query);
        Gson gson = GsonUtils.getGsonBuilder();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        String json = gson.toJson(recipes, type);
        return ApiResponse.getAsJson(true, json);
    }

    @PUT
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateRecipe(String payload) {
        Gson gson = GsonUtils.getGsonBuilder();
        Recipe recipe = gson.fromJson(payload, Recipe.class);
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        recipeDao.updateRecipe(recipe);
        return ApiResponse.getAsJson(true, "success");
    }

    @DELETE
    @Path("/auth/{id}")
    public String removeRecipe(@PathParam("id") int id) {
        RecipeDao recipeDao = DaoFactory.getInstance().getRecipeDao();
        recipeDao.removeRecipeById(id);
        return ApiResponse.getAsJson(true, "success");
    }
}
