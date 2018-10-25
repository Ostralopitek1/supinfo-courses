package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

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
        SupProduct product;
        try {
            long id = Long.parseLong(strId);
            product = SupProductDao.findProductById(id);
        } catch (NumberFormatException e) {
            res.sendError(400, "ID must be a number");
            return;
        } catch (UnknownProductException e) {
            res.sendError(404, "SupProduct not found");
            return;
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher(VIEW).forward(req, res);
    }
}
