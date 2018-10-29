package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.supcommerce.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/addProduct")
public class AddProductServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String content = req.getParameter("content");
        String strPrice = req.getParameter("price");
        float price = 0;
        try {
            price = Float.parseFloat(strPrice);
        } catch (NumberFormatException e) {
            res.sendError(400, "Price must be an integer");
        }
        Product product = new Product();
        product.setName(name);
        product.setContent(content);
        product.setPrice(price);
        // Persist product
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(product);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        res.sendRedirect("/showProduct?id=" + product.getId());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/auth/addProduct.jsp").forward(req, res);
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
