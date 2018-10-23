package com.supfood.servlets.auth.product;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Product;
import com.supfood.entity.Recipe;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/product/remove")
public class RemoveProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String strRecipeId = request.getParameter("recipeId");
        String strProductId = request.getParameter("productId");
        int productId;
        try {
            productId = Integer.parseInt(strProductId);
        } catch (NumberFormatException e) {
            response.sendRedirect("/recipes?page=1");
            return;
        }

        String redirectUrl = "/auth/recipe/edit?id=" + strRecipeId;
        Object objRes = ClientApi.getObject(productId, Product.API_SUFFIX, Product.class);
        if(objRes instanceof ApiResponse) {
            ApiResponse apiResGet = (ApiResponse) objRes;
            apiResGet.redirectWithError(response, redirectUrl);
            return;
        }
        Product product = (Product) objRes;
        product.setActive(false);

        ApiResponse apiResponse = ClientApi.updateObject(product, Product.API_SUFFIX);
        if (!apiResponse.isSuccess()) {
            apiResponse.redirectWithError(response, redirectUrl);
            return;
        }
        response.sendRedirect(redirectUrl);
    }
}