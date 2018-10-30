package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.exception.UnknownProductException;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

import javax.persistence.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/showProduct")
public class ShowProductServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/showProduct.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Try to parse the ID from parameters
        String strId = req.getParameter("id");
        Product product;
        try {
            long id = Long.parseLong(strId);
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            product = productDao.findProductById(id);
        } catch (NumberFormatException e) {
            res.sendError(400, "ID must be a number");
            return;
        } catch (NoResultException e) {
            res.sendError(404, "SupProduct not found");
            return;
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher(VIEW).forward(req, res);
    }
}
