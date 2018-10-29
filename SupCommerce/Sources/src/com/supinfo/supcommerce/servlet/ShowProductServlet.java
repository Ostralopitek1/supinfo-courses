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
        // If everything went well, send the product details
        res.getWriter().write("ID: " + product.getId());
        res.getWriter().write("\nName: " + product.getName());
        res.getWriter().write("\nContent: " + product.getContent());
        res.getWriter().write("\nPrice: $" + product.getPrice());
    }
}
