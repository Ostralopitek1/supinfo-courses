package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/addProduct")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String content = req.getParameter("content");
        String strPrice = req.getParameter("price");
        float price = 0;
        try {
            price = Float.parseFloat(strPrice);
        } catch (NumberFormatException e) {
            res.sendError(400, "Price must be an integer");
        }
        SupProduct product = new SupProduct();
        product.setName(name);
        product.setContent(content);
        product.setPrice(price);
        SupProductDao.addProduct(product);
        res.sendRedirect("/showProduct?id=" + product.getId());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/auth/addProduct.jsp").forward(req, res);
    }
}
