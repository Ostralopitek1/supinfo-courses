package com.supinfo.supcommerce.dao.jpa;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaCategoryDao extends JpaObjectDao implements CategoryDao {
    public JpaCategoryDao(EntityManagerFactory emf) {
        this.emf = emf;
        this.clazz = Category.class;
    }

    @Override
    public void addCategory(Category category) {
        this.addObject(category);
    }

    @Override
    public void removeCategory(Category category) {
        this.removeObject(category);
    }

    @Override
    public Category findCategoryById(long id) {
        return (Category) this.findObjectById(id);
    }

    @Override
    public List<Category> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c").getResultList();
        } finally {
            em.close();
        }
    }
}
