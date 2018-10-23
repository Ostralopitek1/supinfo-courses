package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.dao.CommonDao;
import com.supfood.api.server.dao.UserDao;
import com.supfood.entity.Product;
import com.supfood.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class JpaCommonDao implements CommonDao {

    private final EntityManagerFactory emf;

    public JpaCommonDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Object requestCustomQuery(String query) {
        EntityManager em = emf.createEntityManager();
        List resultList;
        try {
            em.getTransaction().begin();
            resultList = em.createQuery(query).getResultList();
        } finally {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            em.close();
        }
        if (resultList.isEmpty()) return null;
        return resultList.get(0);
    }

    @Override
    public int getUsersCount() {
        Object res = requestCustomQuery("SELECT Count(*) FROM User").toString();
        return res == null ? 0 : Integer.parseInt(res.toString());
    }

    @Override
    public int getRecipesCount() {
        Object res = requestCustomQuery("SELECT Count(*) FROM Recipe WHERE active=true").toString();
        return res == null ? 0 : Integer.parseInt(res.toString());
    }

    @Override
    public String getMostUsedProductName() {
        Object res = requestCustomQuery("SELECT LOWER(p.name) " +
                "FROM Product p " +
                "GROUP BY LOWER(p.name) " +
                "ORDER BY Count(p.name) DESC");
        return res == null ? "Aucun" : res.toString();
    }

    @Override
    public double getAvgMarks() {
        Object res = requestCustomQuery("SELECT AVG(amount) FROM Mark m WHERE m.recipe.active=true");
        return res == null ? 0.0 : Double.parseDouble(res.toString());
    }
}
