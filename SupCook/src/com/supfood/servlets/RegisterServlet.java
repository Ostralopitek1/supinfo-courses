package com.supfood.servlets;

import com.google.gson.Gson;
import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastName = request.getParameter("lastname");
		String firstName = request.getParameter("firstname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String postalCode = request.getParameter("postalCode");
		String password = request.getParameter("pswd2");
		User user = new User(username, firstName, lastName, email, postalCode, password);
		Gson gson = new Gson();
		String jsonUser = gson.toJson(user);
		String handlerUrl = User.API_SUFFIX + "/register";
		ApiResponse apiResponse = ClientApi.callHandler(handlerUrl, jsonUser);
		// Check if request was successful or show error
		if (!apiResponse.isSuccess()) {
			apiResponse.redirectWithError(response, "/register");
			return;
		}
		response.sendRedirect("/");
	}
}
