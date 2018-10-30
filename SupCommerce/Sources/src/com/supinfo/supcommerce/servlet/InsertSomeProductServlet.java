package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.supcommerce.entity.Category;
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

@WebServlet(urlPatterns = "/auth/basicInsert")
public class InsertSomeProductServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }
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
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(cat);
            t.commit();
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        res.getWriter().write("Product and category added");
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
