package com.supfood.servlets.auth;
import com.supfood.api.client.ClientApi;
import com.supfood.entity.ApiResponse;
import com.supfood.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/auth/profile.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastName = request.getParameter("lastname");
        String firstName = request.getParameter("firstname");
        String email = request.getParameter("email");
        String postalCode = request.getParameter("postalCode");
        String password = request.getParameter("pswd2");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        User copyUser = new User(user);

        if(lastName != ""){
            copyUser.setLastName(lastName);
        }

        if(firstName != ""){
            copyUser.setFirstName(firstName);
        }

        if(email != ""){
            copyUser.setEmail(email);
        }

        if(postalCode != ""){
            copyUser.setPostalCode(postalCode);
        }

        if(password != ""){
            copyUser.setPassword(password);
        }

        ApiResponse resUpdateObject = ClientApi.updateObject(copyUser, User.API_SUFFIX);

        if (!resUpdateObject.isSuccess()) {
            resUpdateObject.redirectWithError(response, "/auth/profile");
            return;
        }
        session.setAttribute("user", copyUser);

        response.sendRedirect("/auth/profile");
    }
}