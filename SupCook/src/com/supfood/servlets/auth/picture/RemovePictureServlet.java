package com.supfood.servlets.auth.picture;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Picture;
import com.supfood.entity.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/picture/remove")
public class RemovePictureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strRecipeId = request.getParameter("recipeId");
        String strPictureId = request.getParameter("pictureId");
        int pictureId;
        try {
            pictureId = Integer.parseInt(strPictureId);
        } catch (NumberFormatException e) {
            response.sendRedirect("/recipes?page=1");
            return;
        }

        String redirectUrl = "/auth/recipe/edit?id=" + strRecipeId;
        Object objRes = ClientApi.getObject(pictureId, Picture.API_SUFFIX, Picture.class);
        if(objRes instanceof ApiResponse) {
            ApiResponse apiResGet = (ApiResponse) objRes;
            apiResGet.redirectWithError(response, redirectUrl);
            return;
        }
        Picture picture = (Picture) objRes;
        picture.setActive(false);

        ApiResponse apiResponse = ClientApi.updateObject(picture, Picture.API_SUFFIX);
        if (!apiResponse.isSuccess()) {
            apiResponse.redirectWithError(response, redirectUrl);
            return;
        }
        response.sendRedirect(redirectUrl);
    }
}