package com.supfood.servlets;

import com.supfood.entity.ApiResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/recipes")
public class RecipeListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int ENTRIES_PER_PAGE = 10;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageNumberValue = request.getParameter("page");
        String strCategory = request.getParameter("category");
        String searchQuery = request.getParameter("search");

        int page;
        try {
            page = Integer.parseInt(pageNumberValue);
        } catch (NumberFormatException e) {
            page = -1;
        }
        int categoryId;
        try {
            categoryId = Integer.parseInt(strCategory);
        } catch (NumberFormatException e) {
            categoryId = -1;
        }

        Object res = ServletUtils.setupRequestRecipeList(request, page, ENTRIES_PER_PAGE, categoryId, searchQuery);

        // Normal case
        if (res == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/recipelist.jsp");
            dispatcher.forward(request, response);
        }
        // Wrong page or category
        else if (res instanceof String) {
            response.sendRedirect((String) res);
        }
        // Api Error
        else if (res instanceof ApiResponse) {
            ApiResponse apiResponse = (ApiResponse) res;
            apiResponse.redirectWithError(response, "/recipes?page=1");
        }
    }
}
