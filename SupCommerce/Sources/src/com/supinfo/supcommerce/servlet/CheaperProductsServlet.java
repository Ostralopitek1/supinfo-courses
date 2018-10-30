package com.supinfo.supcommerce.servlet;

import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletConfig;
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
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT p FROM Product AS p WHERE p.price < 100");
        List<Product> products = query.getResultList();
        em.close();
        req.setAttribute("products", products);
        req.getRequestDispatcher(VIEW).forward(req, res);
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
