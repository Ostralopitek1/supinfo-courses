package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/listProduct")
public class ListProductServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/listProduct.jsp";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ProductDao productDao = DaoFactory.getInstance().getProductDao();
        req.setAttribute("products", productDao.getAll());
        req.getRequestDispatcher(VIEW).forward(req, res);
    }
}
