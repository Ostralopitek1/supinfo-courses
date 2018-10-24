package com.supfood.servlets.auth.product;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Product;
import com.supfood.entity.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/product/add")
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipeId = request.getParameter("recipeId");
        String productName = request.getParameter("productName");

        Recipe recipe = (Recipe) ClientApi.getObject(Integer.parseInt(recipeId), Recipe.API_SUFFIX, Recipe.class);

        Product product = new Product();
        product.setRecipe(recipe);
        product.setName(productName);
        product.setActive(true);

        ApiResponse apiResponse = ClientApi.createObject(product, Product.API_SUFFIX);
        String redirectUrl = "/auth/recipe/edit?id=" + recipeId;
        if (!apiResponse.isSuccess()) {
            apiResponse.redirectWithError(response, redirectUrl);
            return;
        }
        response.sendRedirect(redirectUrl);
    }
}
