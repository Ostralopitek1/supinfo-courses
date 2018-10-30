package com.supinfo.supcommerce.servlet.auth;

import com.supinfo.supcommerce.entity.Category;
import org.hibernate.Transaction;

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

@WebServlet(urlPatterns = "/auth/addCategory")
public class AddCategoryServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/auth/addCategory.jsp";

    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        Category cat = new Category();
        cat.setName(name);
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
        res.sendRedirect("/listProduct");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher(VIEW).forward(req, res);
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
