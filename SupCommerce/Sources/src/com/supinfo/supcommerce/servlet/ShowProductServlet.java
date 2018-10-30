package com.supinfo.supcommerce.servlet;

import com.supinfo.sun.supcommerce.exception.UnknownProductException;
import com.supinfo.supcommerce.entity.Product;

import javax.persistence.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/showProduct")
public class ShowProductServlet extends HttpServlet {
    private final static String VIEW = "/WEB-INF/showProduct.jsp";
    private EntityManagerFactory emf;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Try to parse the ID from parameters
        String strId = req.getParameter("id");
        Product product;
        EntityManager em = emf.createEntityManager();
        try {
            long id = Long.parseLong(strId);
            Query query = em.createQuery("SELECT p FROM Product AS p WHERE p.id=:id");
            query.setParameter("id", id);
            product = (Product) query.getSingleResult();
        } catch (NumberFormatException e) {
            res.sendError(400, "ID must be a number");
            return;
        } catch (NoResultException e) {
            res.sendError(404, "SupProduct not found");
            return;
        } finally {
            em.close();
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher(VIEW).forward(req, res);
    }

    @Override
    public void destroy() {
        emf.close();
    }
}
