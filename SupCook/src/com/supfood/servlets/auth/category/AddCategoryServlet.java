package com.supfood.servlets.auth.category;

import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.Category;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/auth/category/add")
public class AddCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/auth/addcat.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");

        Category category = new Category();
        category.setName(name);

        ApiResponse resCreateCategory = ClientApi.createObject(category, Category.API_SUFFIX);

        if (!resCreateCategory.isSuccess()) {
            resCreateCategory.redirectWithError(response, "/recipes");
            return;
        }
        response.sendRedirect("/recipes");
    }
}
