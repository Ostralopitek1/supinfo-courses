package com.supinfo.supcommerce.servlet.auth;

import javax.persistence.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/auth/removeProduct")
public class RemoveProductServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String strId = req.getParameter("id");
        EntityManager em = emf.createEntityManager();;
        EntityTransaction t = em.getTransaction();
        try {
            long id = Long.parseLong(strId);
            t.begin();
            Query query = em.createQuery("DELETE FROM Product AS p WHERE p.id=:id");
            query.setParameter("id", id);
            int deleted = query.executeUpdate();
            t.commit();
            if (deleted < 1) {
                res.sendError(404, "SupProduct not found");
                return;
            }
        } catch (NumberFormatException e) {
            res.sendError(400, "ID must be a number");
            return;
        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }
        res.sendRedirect("/listProduct");
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
