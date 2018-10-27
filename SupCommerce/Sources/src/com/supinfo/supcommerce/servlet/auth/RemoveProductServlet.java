package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

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
        SupProduct product;
        try {
            long id = Long.parseLong(strId);
            SupProductDao.removeProduct(id);
        } catch (NumberFormatException e) {
            res.sendError(400, "ID must be a number");
            return;
        } catch (UnknownProductException e) {
            res.sendError(404, "SupProduct not found");
            return;
        }
        res.sendRedirect("/listProduct");
    }
}
