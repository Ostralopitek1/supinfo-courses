package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/removeProduct")
public class RemoveProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String strId = req.getParameter("id");
        try {
            long id = Long.parseLong(strId);
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            productDao.removeProductById(id);
        } catch (NumberFormatException e) {
            res.sendError(400, "ID must be a number");
            return;
        }
        res.sendRedirect("/listProduct");
    }
}
