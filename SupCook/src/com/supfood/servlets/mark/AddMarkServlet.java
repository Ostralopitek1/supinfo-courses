package com.supfood.servlets.mark;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.Mark;
import com.supfood.entity.Recipe;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/marks")
public class AddMarkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeId = request.getParameter("recipeId");
        String amount = request.getParameter("star");

        Recipe recipe = (Recipe) ClientApi.getObject(Integer.parseInt(recipeId), Recipe.API_SUFFIX, Recipe.class);

        Mark mark = new Mark();
        mark.setAmount(Integer.parseInt(amount));
        mark.setRecipe(recipe);
        mark.setUser(recipe.getOwner());

        ClientApi.createObject(mark, Mark.API_SUFFIX);

        response.sendRedirect("/recipes");
    }
}
