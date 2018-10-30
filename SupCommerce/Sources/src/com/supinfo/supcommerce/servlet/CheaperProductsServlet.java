package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cheapProducts")
public class CheaperProductsServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/listProduct.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        List<Product> products = productDao.getCheaperProducts(100);
        req.setAttribute("products", products);
        req.getRequestDispatcher(VIEW).forward(req, res);
    }
}
