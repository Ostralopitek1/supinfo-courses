package com.supfood.servlets.auth.picture;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Picture;
import com.supfood.entity.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/picture/add")
public class AddPictureServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeId = request.getParameter("recipeId");
        String pictureUrl = request.getParameter("pictureUrl");

        Recipe recipe = (Recipe) ClientApi.getObject(Integer.parseInt(recipeId), Recipe.API_SUFFIX, Recipe.class);

        Picture picture = new Picture();
        picture.setRecipe(recipe);
        picture.setUrl(pictureUrl);
        picture.setActive(true);

        ApiResponse apiResponse = ClientApi.createObject(picture, Picture.API_SUFFIX);
        String redirectUrl = "/auth/recipe/edit?id=" + recipeId;
        if (!apiResponse.isSuccess()) {
            apiResponse.redirectWithError(response, redirectUrl);
            return;
        }
        response.sendRedirect(redirectUrl);
    }
}
