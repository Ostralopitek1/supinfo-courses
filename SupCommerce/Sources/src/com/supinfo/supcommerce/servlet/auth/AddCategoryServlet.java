package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/addCategory")
public class AddCategoryServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/auth/addCategory.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category cat = new Category();
        cat.setName(name);
        CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
        categoryDao.addCategory(cat);
        res.sendRedirect("/listProduct");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, res);
    }
}
