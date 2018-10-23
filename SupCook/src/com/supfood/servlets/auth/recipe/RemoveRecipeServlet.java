package com.supfood.servlets.auth.recipe;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Recipe;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/recipe/remove")
public class RemoveRecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strRecipeId = request.getParameter("recipeId");
        int recipeId;
        try {
            recipeId = Integer.parseInt(strRecipeId);
        } catch (NumberFormatException e) {
            response.sendRedirect("/recipes?page=1");
            return;
        }
        Recipe recipe = (Recipe) ClientApi.getObject(recipeId, Recipe.API_SUFFIX, Recipe.class);
        recipe.setActive(false);
        ApiResponse apiResponse = ClientApi.updateObject(recipe, Recipe.API_SUFFIX);
        if (!apiResponse.isSuccess()) {
            apiResponse.redirectWithError(response, "/recipes?id=" + recipeId);
            return;
        }
        response.sendRedirect("/recipes?page=1");
    }
}