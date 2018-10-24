package com.supfood.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get POST parameters
        String identifier = request.getParameter("identifier");
        String password = request.getParameter("password");

        // Create Json string from data
        JsonObject credentials = new JsonObject();
        credentials.addProperty("identifier", identifier);
        credentials.addProperty("password", password);
        Gson gson = new Gson();
        String jsonCredentials = gson.toJson(credentials);

        // Request API
        String resourceUrl = User.API_SUFFIX + "/login";
        ApiResponse apiResponse = ClientApi.callHandler(resourceUrl, jsonCredentials);

        // Check if request was successful or show error
        if (!apiResponse.isSuccess()) {
            apiResponse.redirectWithError(response, "/login");
            return;
        }

        // Login user
        HttpSession session = request.getSession();
        User user = gson.fromJson(apiResponse.getContent(), User.class);
        session.setAttribute("user", user);
        response.sendRedirect("/");
    }
}
