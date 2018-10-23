package com.supfood.servlets.auth.recipe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supfood.api.client.ClientApi;
import com.supfood.api.client.Utils;
import com.supfood.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet("/auth/recipe/edit")
public class EditRecipeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        int recipeId;
        try {
            recipeId = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            response.sendRedirect("/recipes");
            return;
        }

        Gson gson = new Gson();
        ApiResponse resCategories = ClientApi.getAllObject(Category.API_SUFFIX);
        Type lsCatType = new TypeToken<List<Category>>(){}.getType();
        List<Category> categories = gson.fromJson(resCategories.getContent(), lsCatType);

        String resourceProductUrl = Product.API_SUFFIX + "/recipe/" + recipeId;
        ApiResponse resProducts = ClientApi.getRequest(resourceProductUrl);
        Type lsProductsType = new TypeToken<List<Product>>(){}.getType();
        List<Product> products = gson.fromJson(resProducts.getContent(), lsProductsType);

        String resourcePictureUrl = Picture.API_SUFFIX + "/recipe/" + recipeId;
        ApiResponse resPictures = ClientApi.getRequest(resourcePictureUrl);
        Type lsPicturesType = new TypeToken<List<Picture>>(){}.getType();
        List<Product> pictures = gson.fromJson(resPictures.getContent(), lsPicturesType);

        Recipe recipe = (Recipe) ClientApi.getObject(recipeId, Recipe.API_SUFFIX, Recipe.class);
        request.setAttribute("recipe", recipe);
        request.setAttribute("category", recipe.getCategory());
        request.setAttribute("products", products);
        request.setAttribute("pictures", pictures);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/auth/editrecipe.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        String difficulty = request.getParameter("difficulty");
        String name = request.getParameter("name");
        String category_id = request.getParameter("category_id");
        String picture_url = request.getParameter("picture_url");
        String recipeId = request.getParameter("recipeId");
        String strCookingTime = request.getParameter("cookingTime");
        String strPreparationTime = request.getParameter("preparationTime");

        String recipeEditUrl = request.getRequestURI() + "?id=" + recipeId;
        int cookingTime, preparationTime;
        try {
            cookingTime = Integer.parseInt(strCookingTime) * 60;
            preparationTime = Integer.parseInt(strPreparationTime);
        } catch (NumberFormatException e) {
            Utils.redirectWithError(response, recipeEditUrl, "INVALID_FIELDS");
            return;
        }

        Recipe recipe = (Recipe) ClientApi.getObject(Integer.parseInt(recipeId), Recipe.API_SUFFIX, Recipe.class);
        Category category = (Category)ClientApi.getObject(Integer.parseInt(category_id) ,Category.API_SUFFIX,Category.class);

        recipe.setName(name);
        recipe.setPreparationTime(preparationTime);
        recipe.setCookingTime(cookingTime);
        recipe.setDifficulty(Integer.parseInt(difficulty));
        recipe.setCategory(category);
        if (description != "") {
            recipe.setDescription(description);
        }

        ApiResponse resUpdateRecipe = ClientApi.updateObject(recipe, Recipe.API_SUFFIX);

        if (!resUpdateRecipe.isSuccess()) {
            resUpdateRecipe.redirectWithError(response, recipeEditUrl);
            return;
        }

        if (picture_url != "") {
            Picture picture = new Picture();
            picture.setRecipe(recipe);
            picture.setUrl(picture_url);

            ApiResponse resCreatePicture = ClientApi.updateObject(picture, Picture.API_SUFFIX);
            if (!resCreatePicture.isSuccess()) {
                resCreatePicture.redirectWithError(response, recipeEditUrl);
                return;
            }
        }
        response.sendRedirect(recipeEditUrl);
    }
}
