package com.supfood.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApiResponse apiResponse = ClientApi.getRequest("stats");
        if (!apiResponse.isSuccess()) {
            response.getWriter().write(apiResponse.getContent());
            return;
        }
        Gson gson = new Gson();
        JsonObject stats = gson.fromJson(apiResponse.getContent(), JsonObject.class);

        int usersCount = stats.get("usersCount").getAsInt();
        int recipesCount = stats.get("recipesCount").getAsInt();
        double avgMarks = stats.get("avgMarks").getAsDouble();
        String mostUsedProduct = stats.get("mostUsedProduct").getAsString();

        request.setAttribute("usersCount", usersCount);
        request.setAttribute("recipesCount", recipesCount);
        request.setAttribute("avgMarks", avgMarks);
        request.setAttribute("mostUsedProduct", mostUsedProduct);

        ServletUtils.setupRequestRecipeList(request, 1, 10, -1, null);

        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
