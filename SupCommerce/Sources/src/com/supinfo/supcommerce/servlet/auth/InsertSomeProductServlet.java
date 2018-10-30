package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.entity.Category;
import com.supinfo.supcommerce.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Instantiate product
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(10);
        product.setContent("Content of the first product");
        // Instantiate category
        Category cat = new Category();
        cat.setName("Category 1");
        cat.addProduct(product);
        // Persist
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        categoryDao.addCategory(cat);
        res.getWriter().write("Product and category added");
    }
}
