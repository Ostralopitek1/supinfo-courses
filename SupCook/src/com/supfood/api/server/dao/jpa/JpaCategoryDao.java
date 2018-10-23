package com.supfood.api.server.dao.jpa;

import com.supfood.api.server.dao.CategoryDao;
import com.supfood.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class JpaCategoryDao implements CategoryDao {

    private final EntityManagerFactory emf;


    public JpaCategoryDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addCategory(Category category) {
        JpaObjectDao.addObject(emf, category, category.getId(), Category.class);
    }

    @Override
    public void updateCategory(Category category) {
        JpaObjectDao.updateObject(emf, category, category.getId(), Category.class);
    }

    @Override
    public void removeCategoryById(int id) {
        JpaObjectDao.removeObjectById(emf, id, Category.class);
    }

    @Override
    public void removeCategory(Category category) {
        removeCategoryById(category.getId());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> getAllCategories() {
        List<Category> result = new ArrayList<Category>();
        EntityManager em = emf.createEntityManager();
        try {
            result.addAll(em.createQuery("SELECT c FROM Category c").getResultList());
        } finally {
            em.close();
        }

        return result;
    }

    @Override
    public Category getCategory(int id) {
        Object object = JpaObjectDao.getObject(emf, id, Category.class);
        if (object == null) {
            return null;
        }
        return (Category) object;
    }
}
