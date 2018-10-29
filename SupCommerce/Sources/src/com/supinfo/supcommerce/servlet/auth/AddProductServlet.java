package com.supinfo.supcommerce.servlet.auth;

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
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }

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
        // Play with DB
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            // Get and set category
            Query query = em.createQuery("SELECT c FROM Category AS c WHERE id=:id");
            query.setParameter("id", categoryId);
            Category category = (Category) query.getSingleResult();
            product.setCategory(category);
            // Persist product
            t.begin();
            em.persist(product);
            t.commit();
        } catch (NoResultException e) {
            res.sendError(404, "Category not found");
            return;
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        res.sendRedirect("/showProduct?id=" + product.getId());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Category AS c");
        List<Category> categories = query.getResultList();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher(VIEW).forward(req, res);
        em.close();
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
