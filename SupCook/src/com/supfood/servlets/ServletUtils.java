package com.supfood.servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.client.ClientApi;
import com.supfood.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ServletUtils {

    public static Object setupRequestRecipeList(HttpServletRequest request, int page, int entriesPerPage, int categoryId, String searchQuery) throws IOException, ServletException {


        String redirectUrl = "/recipes?";
        if(page == -1) {
            redirectUrl += "page=" + 1;
            if (categoryId != -1) {
                redirectUrl += "&category=" + categoryId;
            }
            if(searchQuery != null) {
                redirectUrl += "&search=" + searchQuery;
            }
            return redirectUrl;
        }

        // Categories
        Gson gson = new Gson();
        ApiResponse resCategories = ClientApi.getAllObject(Category.API_SUFFIX);
        if(!resCategories.isSuccess()) {
            return resCategories;
        }

        Type lsCategoryType = new TypeToken<List<Category>>(){}.getType();
        List<Category> categories = gson.fromJson(resCategories.getContent(), lsCategoryType);
        request.setAttribute("categories", categories);
        request.setAttribute("currentCategoryId", categoryId);

        // Recipes
        String apiResource = Recipe.API_SUFFIX;
        if (searchQuery != null) {
            apiResource += "/search/" + searchQuery;
        }
        else if (categoryId != -1) {
            apiResource += "/category/" + categoryId;
        }

        ApiResponse resRecipes = ClientApi.getRequest(apiResource);
        if(!resRecipes.isSuccess()) {
            return resRecipes;
        }

        // Deserialize recipes list
        Type lsRecipeType = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> recipes = gson.fromJson(resRecipes.getContent(), lsRecipeType);
        request.setAttribute("recipes", getRecipePage(recipes, page, entriesPerPage));

        // Pages
        request.setAttribute("pages", getPages(recipes, entriesPerPage));
        request.setAttribute("currentPage", page);
        return null;
    }

    static List<Integer> getPages(List list, int entriesPerPage) {
        List<Integer> pagesList = new ArrayList<>();
        int pages = list.size() / entriesPerPage;
        if (list.size() % entriesPerPage != 0) {
            pages = pages + 1;
        }
        for (int i = 1; i <= pages; i++) {
            pagesList.add(i);
        }
        return pagesList;
    }

    static List<Recipe> getRecipePage(List<Recipe> allRecipes, int page, int entriesPerPage) {
        if(allRecipes.size() == 0)
            return null;
        int startIndex = page * entriesPerPage - entriesPerPage;
        int endIndex = startIndex + entriesPerPage;
        int maxIndex = allRecipes.size();
        if (startIndex > maxIndex) {
            startIndex = maxIndex;
        }
        if (endIndex > maxIndex) {
            endIndex = maxIndex;
        }
        return allRecipes.subList(startIndex, endIndex);
    }

    public static int getRecipeAvgMark(Recipe recipe){

        Gson gson = new Gson();

        int RecipeAvgMarks = 0;
        int RecipeAvgSum = 0;

        ApiResponse apiResponse = ClientApi.getRequest(Mark.API_SUFFIX + "/recipe/" + recipe.getId());
        Type MarksTypeList = new TypeToken<List<Mark>>(){}.getType();
        List<Mark> marks = gson.fromJson(apiResponse.getContent(), MarksTypeList);

        if (marks.size() != 0) {
            for (int i = 0; i < marks.size(); i++) {
                RecipeAvgSum += marks.get(i).getAmount();
            }
            RecipeAvgMarks = RecipeAvgSum / marks.size();
        }
        return RecipeAvgMarks;
    }

    public static int getRecipeUserAvgMark(User user){

        Gson gson = new Gson();
        int RecipeUserAvgMarks = 0;
        int RecipeAvgSum = 0;
        int RecipeCount = 0;

        ApiResponse resGetAllRecipes = ClientApi.getAllObject(Recipe.API_SUFFIX);
        Type recipesTypeList = new TypeToken<List<Recipe>>(){}.getType();
        List<Recipe> recipes = gson.fromJson(resGetAllRecipes.getContent(), recipesTypeList);

        for (int j = 0; j < recipes.size(); j++) {
            if (recipes.get(j).getId() == user.getId()){
                ApiResponse apiResponse = ClientApi.getRequest(Mark.API_SUFFIX + "/recipe/" + recipes.get(j).getId());
                Type MarksTypeList = new TypeToken<List<Mark>>(){}.getType();
                List<Mark> marks = gson.fromJson(apiResponse.getContent(), MarksTypeList);

                if (marks.size() != 0) {
                    for (int i = 0; i < marks.size(); i++) {
                        RecipeAvgSum += marks.get(i).getAmount();
                        RecipeCount ++;
                    }
                }
            }
        }

        if (RecipeCount !=0 ){
            RecipeUserAvgMarks = RecipeAvgSum / RecipeCount;
        }
        return RecipeUserAvgMarks;
    }
}
