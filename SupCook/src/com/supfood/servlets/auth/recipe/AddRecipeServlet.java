package com.supfood.servlets.auth.recipe;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Category;
import com.supfood.entity.Recipe;
import com.supfood.entity.User;
import com.supfood.entity.Picture;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.reflect.TypeToken;


@WebServlet("/auth/recipe/add")
public class AddRecipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddRecipeServlet() {
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        ApiResponse resGetAllCategory = ClientApi.getAllObject(Category.API_SUFFIX);
        Type TypeList = new TypeToken<List<Category>>(){}.getType();
        List<Category> categories = gson.fromJson(resGetAllCategory.getContent(), TypeList);

        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/auth/addrecipe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookingTime = request.getParameter("cookingTime");
        String description = request.getParameter("description");
        String difficulty = request.getParameter("difficulty");
        String name = request.getParameter("name");
        String preparationTime = request.getParameter("preparationTime");
        String category_id = request.getParameter("category_id");

        User user = (User) request.getSession().getAttribute("user");
        Category category = (Category) ClientApi.getObject(Integer.parseInt(category_id), Category.API_SUFFIX, Category.class);

        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setDescription(description);
        recipe.setPreparationTime(Integer.parseInt(preparationTime));
        recipe.setCookingTime(Integer.parseInt(cookingTime)*60);
        recipe.setDifficulty(Integer.parseInt(difficulty));
        recipe.setCategory(category);
        recipe.setOwner(user);
        recipe.setActive(true);

        ApiResponse resCreateRecipe = ClientApi.createObject(recipe, Recipe.API_SUFFIX);
        if (!resCreateRecipe.isSuccess()) {
            resCreateRecipe.redirectWithError(response, "/recipes");
            return;
        }

        response.sendRedirect("/recipes");
    }
}
