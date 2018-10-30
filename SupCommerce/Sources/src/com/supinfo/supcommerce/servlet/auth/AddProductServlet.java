package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Category;
import com.supinfo.supcommerce.entity.Product;

import javax.persistence.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/auth/addProduct")
public class AddProductServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/auth/addProduct.jsp";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Parse parameters
        String name = req.getParameter("name");
        String content = req.getParameter("content");
        String strPrice = req.getParameter("price");
        String strCategoryId = req.getParameter("categoryId");
        float price = 0;
        long categoryId = 0;
        try {
            price = Float.parseFloat(strPrice);
            categoryId = Long.parseLong(strCategoryId);
        } catch (NumberFormatException e) {
            res.sendError(400, "Price and categoryId must be numbers");
        }
        // Instantiate product
        Product product = new Product();
        product.setName(name);
        product.setContent(content);
        product.setPrice(price);
        try {
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            Category category = categoryDao.findCategoryById(categoryId);
            product.setCategory(category);
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            productDao.addProduct(product);
        } catch (NoResultException e) {
            res.sendError(404, "Category not found");
            return;
        }
        res.sendRedirect("/showProduct?id=" + product.getId());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        req.setAttribute("categories", categoryDao.getAll());
        req.getRequestDispatcher(VIEW).forward(req, res);
    }
}
