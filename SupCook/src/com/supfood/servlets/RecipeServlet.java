package com.supfood.servlets;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.Category;
import com.supfood.entity.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/recipe")
public class RecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("id");
        int recipeId;
        try {
            recipeId = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            response.sendRedirect("/recipes?page=1");
            return;
        }
        Recipe recipe = (Recipe) ClientApi.getObject(recipeId, Recipe.API_SUFFIX, Recipe.class);
        Category category = recipe.getCategory();

        request.setAttribute("recipe", recipe);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/WEB-INF/recipe.jsp").forward(request, response);
    }
}
