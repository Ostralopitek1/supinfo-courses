package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

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
        SupProduct product = new SupProduct();
        product.setName("Toto");
        product.setPrice(10);
        product.setContent("Titi");
        SupProductDao.addProduct(product);
        res.getWriter().write("Product added");
    }
}
